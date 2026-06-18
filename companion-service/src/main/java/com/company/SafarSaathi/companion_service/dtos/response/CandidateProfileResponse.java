package com.company.SafarSaathi.companion_service.dtos.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileResponse {

    private CompanionResponse companion;

    private UserProfileResponse userProfile;

    private CompanionPreferenceResponse preference;
}