package com.company.SafarSaathi.companion_service.service;

import com.company.SafarSaathi.common.events.NotificationEvent;
import com.company.SafarSaathi.companion_service.auth.UserContextHolder;
import com.company.SafarSaathi.companion_service.client.UserServiceClient;
import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import com.company.SafarSaathi.companion_service.dtos.request.SendCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionRequestResponse;
import com.company.SafarSaathi.companion_service.entity.CompanionRequest;
import com.company.SafarSaathi.companion_service.enums.RequestStatus;
import com.company.SafarSaathi.companion_service.exceptions.BadRequestException;
import com.company.SafarSaathi.companion_service.exceptions.ResourceNotFoundException;
import com.company.SafarSaathi.companion_service.repository.CompanionRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CompanionRequestService {

    private final CompanionRequestRepository companionRequestRepository;
    private final ModelMapper modelMapper;
    private final NotificationEventProducer notificationEventProducer;
    private final RequestGraphService requestGraphService;
    private final UserServiceClient userServiceClient;

    public CompanionRequestResponse sendRequest(
            SendCompanionRequest requestDto
    ) {

        Long senderId = UserContextHolder.getCurrentUserId();

        log.info(
                "Sending companion request from userId={} to userId={}",
                senderId,
                requestDto.getReceiverId()
        );

        if (senderId.equals(requestDto.getReceiverId())) {
            throw new BadRequestException(
                    "You cannot send a companion request to yourself"
            );
        }

        companionRequestRepository
                .findBySenderIdAndReceiverIdAndTripId(
                        senderId,
                        requestDto.getReceiverId(),
                        requestDto.getTripId()
                )
                .ifPresent(request -> {
                    throw new BadRequestException(
                            "Companion request already exists"
                    );
                });

        // Validate receiver exists
        UserProfileResponse receiverProfile;

        try {

            receiverProfile =
                    userServiceClient.getUserProfile(
                            requestDto.getReceiverId()
                    );

        } catch (Exception ex) {

            log.error(
                    "Unable to fetch receiver profile. receiverId={}",
                    requestDto.getReceiverId(),
                    ex
            );

            throw new ResourceNotFoundException(
                    "Receiver user does not exist"
            );
        }

        CompanionRequest companionRequest =
                CompanionRequest.builder()
                        .senderId(senderId)
                        .receiverId(requestDto.getReceiverId())
                        .tripId(requestDto.getTripId())
                        .message(requestDto.getMessage())
                        .status(RequestStatus.PENDING)
                        .build();

        CompanionRequest savedRequest =
                companionRequestRepository.save(
                        companionRequest
                );

        log.info(
                "Companion request created successfully. requestId={}",
                savedRequest.getId()
        );

        NotificationEvent event =
                NotificationEvent.builder()
                        .userId(
                                receiverProfile.getUserId().toString()
                        )
                        .email(
                                receiverProfile.getEmail()
                        )
                        .type("REQUEST_RECEIVED")
                        .message(
                                "You have received a companion request for trip "
                                        + requestDto.getTripId()
                        )
                        .build();

        notificationEventProducer.sendNotification(event);

        requestGraphService.saveRequestToGraph(
                senderId,
                requestDto.getReceiverId(),
                requestDto.getTripId(),
                RequestStatus.PENDING.name()
        );

        return modelMapper.map(
                savedRequest,
                CompanionRequestResponse.class
        );
    }

    public CompanionRequestResponse acceptRequest(
            Long requestId
    ) {

        Long currentUserId =
                UserContextHolder.getCurrentUserId();

        CompanionRequest request =
                companionRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Companion request not found with id: "
                                                + requestId
                                )
                        );

        if (!request.getReceiverId().equals(currentUserId)) {
            throw new BadRequestException(
                    "You are not authorized to accept this request"
            );
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new BadRequestException(
                    "Only pending requests can be accepted"
            );
        }

        request.setStatus(RequestStatus.ACCEPTED);

        CompanionRequest updatedRequest =
                companionRequestRepository.save(request);

        notificationEventProducer.sendNotification(
                NotificationEvent.builder()
                        .userId(
                                request.getSenderId().toString()
                        )
                        .type("REQUEST_ACCEPTED")
                        .message(
                                "Your companion request has been accepted"
                        )
                        .build()
        );

        log.info(
                "Request accepted. requestId={}, receiverId={}",
                requestId,
                currentUserId
        );

        return modelMapper.map(
                updatedRequest,
                CompanionRequestResponse.class
        );
    }

    public CompanionRequestResponse rejectRequest(
            Long requestId
    ) {

        Long currentUserId =
                UserContextHolder.getCurrentUserId();

        CompanionRequest request =
                companionRequestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Companion request not found with id: "
                                                + requestId
                                )
                        );

        if (!request.getReceiverId().equals(currentUserId)) {
            throw new BadRequestException(
                    "You are not authorized to reject this request"
            );
        }

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new BadRequestException(
                    "Only pending requests can be rejected"
            );
        }

        request.setStatus(RequestStatus.REJECTED);

        CompanionRequest updatedRequest =
                companionRequestRepository.save(request);

        notificationEventProducer.sendNotification(
                NotificationEvent.builder()
                        .userId(
                                request.getSenderId().toString()
                        )
                        .type("REQUEST_REJECTED")
                        .message(
                                "Your companion request has been rejected"
                        )
                        .build()
        );

        log.info(
                "Request rejected. requestId={}, receiverId={}",
                requestId,
                currentUserId
        );

        return modelMapper.map(
                updatedRequest,
                CompanionRequestResponse.class
        );
    }

    @Transactional(readOnly = true)
    public List<CompanionRequestResponse> getReceivedRequests() {

        Long userId =
                UserContextHolder.getCurrentUserId();

        return companionRequestRepository
                .findByReceiverId(userId)
                .stream()
                .map(request ->
                        modelMapper.map(
                                request,
                                CompanionRequestResponse.class
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CompanionRequestResponse> getSentRequests() {

        Long userId =
                UserContextHolder.getCurrentUserId();

        return companionRequestRepository
                .findBySenderId(userId)
                .stream()
                .map(request ->
                        modelMapper.map(
                                request,
                                CompanionRequestResponse.class
                        )
                )
                .toList();
    }
}