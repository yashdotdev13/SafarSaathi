package com.company.SafarSaathi.notification_service.service;


import com.company.SafarSaathi.notification_service.dtos.NotificationResponse;
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
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;


    public List<NotificationResponse> getNotifications(){
        Long userId  = UserCon
    }
}
