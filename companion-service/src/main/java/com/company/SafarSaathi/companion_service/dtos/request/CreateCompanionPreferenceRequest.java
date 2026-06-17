package com.company.SafarSaathi.companion_service.dtos.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanionPreferenceRequest {

    private Integer preferredAgeMin;
    private Integer preferredAgeMax;
    private String preferredGender;
    private Boolean smokerOk;
    private Boolean drinkerOk;
    private String preferredTripMode;
}