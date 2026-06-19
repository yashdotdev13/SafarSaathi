package com.company.SafarSaathi.notification_service.kafka;


import com.company.SafarSaathi.common.events.NotificationEvent;
import com.company.SafarSaathi.notification_service.entity.Notification;
import com.company.SafarSaathi.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(
            topics = "notification-topic",
            groupId = "notification-service-group"
    )
    public void consume(NotificationEvent event) {

        log.info(
                "Received notification event for userId={}",
                event.getUserId()
        );

        Notification notification =
                Notification.builder()
                        .userId(
                                Long.parseLong(
                                        event.getUserId()
                                )
                        )
                        .type(event.getType())
                        .message(event.getMessage())
                        .read(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(notification);

        log.info(
                "Notification saved successfully for userId={}",
                event.getUserId()
        );
    }
}