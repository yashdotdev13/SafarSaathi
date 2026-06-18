package com.company.SafarSaathi.companion_service.dtos.request;


import com.company.SafarSaathi.companion_service.enums.CompanionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompanionRequest {

    @NotNull
    private Long tripId;

    @NotNull
    private CompanionStatus status;
    private String message;
}
