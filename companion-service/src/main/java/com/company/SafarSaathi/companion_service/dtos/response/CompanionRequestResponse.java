package com.company.SafarSaathi.companion_service.dtos.response;

import com.company.SafarSaathi.companion_service.enums.RequestStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanionRequestResponse {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private Long tripId;

    private RequestStatus status;

    private String message;

    private LocalDateTime timeStamp;
}