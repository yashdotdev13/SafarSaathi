package com.company.SafarSaathi.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEvent {
    private String userId;
    private String type;
    private String message;
    private String email;
    private String phoneNumber;

}