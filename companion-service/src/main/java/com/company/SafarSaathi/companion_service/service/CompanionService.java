package com.company.SafarSaathi.companion_service.service;

import com.company.SafarSaathi.companion_service.auth.UserContextHolder;
import com.company.SafarSaathi.companion_service.dtos.request.CreateCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.request.UpdateCompanionRequest;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionResponse;
import com.company.SafarSaathi.companion_service.entity.Companion;
import com.company.SafarSaathi.companion_service.exceptions.BadRequestException;
import com.company.SafarSaathi.companion_service.exceptions.ResourceNotFoundException;
import com.company.SafarSaathi.companion_service.repository.CompanionRepository;
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
public class CompanionService {

    private final CompanionRepository companionRepository;
    private final ModelMapper modelMapper;

    public CompanionResponse createCompanion(
            CreateCompanionRequest request
    ) {

        Long userId = UserContextHolder.getCurrentUserId();

        log.info("Creating companion profile for userId={}", userId);

        Companion companion = Companion.builder()
                .userId(userId)
                .tripId(request.getTripId())
                .status(request.getStatus())
                .message(request.getMessage())
                .build();

        Companion savedCompanion = companionRepository.save(companion);

        log.info(
                "Companion profile created successfully. companionId={}",
                savedCompanion.getId()
        );

        return modelMapper.map(
                savedCompanion,
                CompanionResponse.class
        );
    }

    public CompanionResponse updateCompanion(
            Long companionId,
            UpdateCompanionRequest request
    ) {

        Long userId = UserContextHolder.getCurrentUserId();

        log.info(
                "Updating companionId={} for userId={}",
                companionId,
                userId
        );

        Companion companion = companionRepository.findById(companionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Companion not found with id: " + companionId
                        )
                );

        if (!companion.getUserId().equals(userId)) {
            throw new BadRequestException(
                    "You are not authorized to update this companion profile"
            );
        }

        if (request.getTripId() != null) {
            companion.setTripId(request.getTripId());
        }

        if (request.getStatus() != null) {
            companion.setStatus(request.getStatus());
        }

        if (request.getMessage() != null) {
            companion.setMessage(request.getMessage());
        }

        Companion updatedCompanion =
                companionRepository.save(companion);

        log.info(
                "Companion profile updated successfully. companionId={}",
                updatedCompanion.getId()
        );

        return modelMapper.map(
                updatedCompanion,
                CompanionResponse.class
        );
    }

    public void deleteCompanion(Long companionId) {

        Long userId = UserContextHolder.getCurrentUserId();

        log.info(
                "Deleting companionId={} for userId={}",
                companionId,
                userId
        );

        Companion companion = companionRepository.findById(companionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Companion not found with id: " + companionId
                        )
                );

        if (!companion.getUserId().equals(userId)) {
            throw new BadRequestException(
                    "You are not authorized to delete this companion profile"
            );
        }

        companionRepository.delete(companion);

        log.info(
                "Companion profile deleted successfully. companionId={}",
                companionId
        );
    }

    @Transactional(readOnly = true)
    public List<CompanionResponse> getAllCompanions() {

        Long userId = UserContextHolder.getCurrentUserId();

        log.info(
                "Fetching companion profiles for userId={}",
                userId
        );

        return companionRepository.findByUserId(userId)
                .stream()
                .map(companion ->
                        modelMapper.map(
                                companion,
                                CompanionResponse.class
                        )
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public CompanionResponse getCompanionById(
            Long companionId
    ) {

        Long userId = UserContextHolder.getCurrentUserId();

        Companion companion = companionRepository.findById(companionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Companion not found with id: " + companionId
                        )
                );

        if (!companion.getUserId().equals(userId)) {
            throw new BadRequestException(
                    "You are not authorized to access this companion profile"
            );
        }

        return modelMapper.map(
                companion,
                CompanionResponse.class
        );
    }
}