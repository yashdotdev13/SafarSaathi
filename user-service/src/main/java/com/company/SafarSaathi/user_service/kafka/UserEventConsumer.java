package com.company.SafarSaathi.user_service.kafka;

import com.company.SafarSaathi.common.constants.KafkaTopics;
import com.company.SafarSaathi.common.events.UserRegisteredEvent;
import com.company.SafarSaathi.user_service.entities.User;
import com.company.SafarSaathi.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventConsumer {

    private final UserRepository userRepository;

    @KafkaListener(
            topics = KafkaTopics.USER_REGISTERED,
            groupId = "user-service-group"
    )
    public void consume(UserRegisteredEvent event) {

        log.info(
                "Received UserRegisteredEvent for userId={}",
                event.getUserId()
        );

        if (userRepository.existsById(event.getUserId())) {
            log.warn(
                    "User profile already exists for userId={}",
                    event.getUserId()
            );
            return;
        }

        User user = User.builder()
                .id(event.getUserId())
                .fullName(event.getFullName())
                .email(event.getEmail())
                .build();

        userRepository.save(user);

        log.info(
                "User profile created successfully for userId={}",
                event.getUserId()
        );
    }
}