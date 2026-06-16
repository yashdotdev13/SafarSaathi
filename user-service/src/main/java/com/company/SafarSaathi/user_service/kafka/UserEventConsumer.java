package com.company.SafarSaathi.user_service.kafka;

import com.company.SafarSaathi.common.constants.KafkaTopics;
import com.company.SafarSaathi.common.events.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserEventConsumer {

    @KafkaListener(
            topics = KafkaTopics.USER_REGISTERED,
            groupId = "user-service-group"
    )
    public void consume(
            UserRegisteredEvent event
    ) {

        log.info(
                "Received UserRegisteredEvent for userId={}",
                event.getUserId()
        );

        // next step:
        // create profile
    }
}