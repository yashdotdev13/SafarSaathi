package com.company.SafarSaathi.auth_service.kafka;


import com.company.SafarSaathi.common.constants.KafkaTopics;
import com.company.SafarSaathi.common.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventProducer {


    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;

    public void publishUserRegisteredEvent(UserRegisteredEvent event){
        log.info("Publishing UserRegisteredEvent for userId: {}",event.getUserId());
        kafkaTemplate.send(KafkaTopics.USER_REGISTERED,event);
    }
}
