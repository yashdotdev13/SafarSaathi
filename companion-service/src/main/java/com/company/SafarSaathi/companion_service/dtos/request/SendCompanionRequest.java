package com.company.SafarSaathi.companion_service.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendCompanionRequest {

    @NotNull
    private Long receiverId;

    @NotNull
    private Long tripId;

    private String message;
}