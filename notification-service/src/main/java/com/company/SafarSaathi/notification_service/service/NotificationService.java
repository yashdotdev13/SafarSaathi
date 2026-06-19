package com.company.SafarSaathi.notification_service.service;


import com.company.SafarSaathi.notification_service.auth.UserContextHolder;
import com.company.SafarSaathi.notification_service.dtos.NotificationResponse;
import com.company.SafarSaathi.notification_service.entity.Notification;
import com.company.SafarSaathi.notification_service.exceptions.BadRequestException;
import com.company.SafarSaathi.notification_service.exceptions.ResourceNotFoundException;
import com.company.SafarSaathi.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;


    @Transactional(readOnly = true)
    public List<NotificationResponse> getNotifications(){
        Long userId  = UserContextHolder.getCurrentUserId();

        return notificationRepository
                .findByUserIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(notification ->
                        modelMapper.map(
                                notification,
                                NotificationResponse.class
                        ))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getUnreadNotifications(){

        Long userId = UserContextHolder.getCurrentUserId();

        return notificationRepository.findByUserIdAndReadFalseOrderByCreatedAtDesc(
                userId
        )
                .stream()
                .map(notification ->
                        modelMapper.map(
                                notification,
                                NotificationResponse.class
                        ))
                .toList();
    }


    public NotificationResponse markAsRead(
            Long notificationId
    ) {

        Long currentUserId =
                UserContextHolder.getCurrentUserId();

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Notification not found"
                                )
                        );

        if (!notification.getUserId()
                .equals(currentUserId)) {

            throw new BadRequestException(
                    "You are not authorized to update this notification"
            );
        }

        notification.setRead(true);

        Notification saved =
                notificationRepository.save(notification);

        return modelMapper.map(
                saved,
                NotificationResponse.class
        );
    }



}
