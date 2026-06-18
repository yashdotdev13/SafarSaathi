package com.company.SafarSaathi.companion_service.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanionPreferenceResponse {

    private Long id;

    private Long userId;

    private Integer preferredAgeMin;

    private Integer preferredAgeMax;

    private String preferredGender;

    private Boolean smokerOk;

    private Boolean drinkerOk;

    private String preferredTripMode;
}