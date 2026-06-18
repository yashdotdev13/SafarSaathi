package com.company.SafarSaathi.companion_service.dtos.request;

import com.company.SafarSaathi.companion_service.enums.CompanionStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanionRequest {

    private Long tripId;
    private CompanionStatus status;
    private String message;
}