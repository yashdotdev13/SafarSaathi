package com.company.SafarSaathi.notification_service.kafka;


import com.company.SafarSaathi.common.events.TripCancelledEvent;
import com.company.SafarSaathi.common.events.TripCompletedEvent;
import com.company.SafarSaathi.common.events.TripCreatedEvent;
import com.company.SafarSaathi.common.events.TripUpdatedEvent;
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
public class TripEventConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(
            topics = "trip-created",
            groupId = "notification-service-group"
    )
    public void consumeTripCreated(
            TripCreatedEvent event
    ) {

        log.info(
                "Trip Created Event Received. tripId={}",
                event.getTripId()
        );

        Notification notification =
                Notification.builder()
                        .userId(event.getUserId())
                        .type("TRIP_CREATED")
                        .message(
                                String.format(
                                        "Your trip from %s to %s has been created successfully.",
                                        event.getOrigin(),
                                        event.getDestination()
                                )
                        )
                        .read(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(notification);

        log.info(
                "Trip Created Notification Saved. userId={}",
                event.getUserId()
        );
    }

    @KafkaListener(
            topics = "trip-updated",
            groupId = "notification-service-group"
    )
    public void consumeTripUpdated(
            TripUpdatedEvent event
    ) {

        log.info(
                "Trip Updated Event Received. tripId={}",
                event.getTripId()
        );

        Notification notification =
                Notification.builder()
                        .userId(event.getUserId())
                        .type("TRIP_UPDATED")
                        .message(
                                String.format(
                                        "Your trip from %s to %s has been updated.",
                                        event.getOrigin(),
                                        event.getDestination()
                                )
                        )
                        .read(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(notification);
    }

    @KafkaListener(
            topics = "trip-cancelled",
            groupId = "notification-service-group"
    )
    public void consumeTripCancelled(
            TripCancelledEvent event
    ) {

        log.info(
                "Trip Cancelled Event Received. tripId={}",
                event.getTripId()
        );

        Notification notification =
                Notification.builder()
                        .userId(event.getUserId())
                        .type("TRIP_CANCELLED")
                        .message(
                                "Your trip has been cancelled."
                        )
                        .read(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(notification);
    }

    @KafkaListener(
            topics = "trip-completed",
            groupId = "notification-service-group"
    )
    public void consumeTripCompleted(
            TripCompletedEvent event
    ) {

        log.info(
                "Trip Completed Event Received. tripId={}",
                event.getTripId()
        );

        Notification notification =
                Notification.builder()
                        .userId(event.getUserId())
                        .type("TRIP_COMPLETED")
                        .message(
                                "Congratulations! Your trip has been completed."
                        )
                        .read(false)
                        .createdAt(LocalDateTime.now())
                        .build();

        notificationRepository.save(notification);
    }
}