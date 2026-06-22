package com.company.SafarSaathi.trip_service.kafka;


import com.company.SafarSaathi.common.events.TripCancelledEvent;
import com.company.SafarSaathi.common.events.TripCompletedEvent;
import com.company.SafarSaathi.common.events.TripCreatedEvent;
import com.company.SafarSaathi.common.events.TripUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishTripCreated(
            TripCreatedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRIP_CREATED,
                event.getTripId().toString(),
                event
        );

        log.info(
                "Trip Created Event Published. tripId={}",
                event.getTripId()
        );
    }

    public void publishTripUpdated(
            TripUpdatedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRIP_UPDATED,
                event.getTripId().toString(),
                event
        );

        log.info(
                "Trip Updated Event Published. tripId={}",
                event.getTripId()
        );
    }

    public void publishTripCancelled(
            TripCancelledEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRIP_CANCELLED,
                event.getTripId().toString(),
                event
        );

        log.info(
                "Trip Cancelled Event Published. tripId={}",
                event.getTripId()
        );
    }

    public void publishTripCompleted(
            TripCompletedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRIP_COMPLETED,
                event.getTripId().toString(),
                event
        );

        log.info(
                "Trip Completed Event Published. tripId={}",
                event.getTripId()
        );
    }
}