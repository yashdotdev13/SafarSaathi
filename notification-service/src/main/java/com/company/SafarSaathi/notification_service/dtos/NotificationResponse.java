package com.company.SafarSaathi.notification_service.dtos;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private Long userId;

    private String type;

    private String message;

    private boolean read;

    private LocalDateTime createdAt;
}
