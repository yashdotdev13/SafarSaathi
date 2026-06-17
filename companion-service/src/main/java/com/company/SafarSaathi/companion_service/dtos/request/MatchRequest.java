package com.company.SafarSaathi.companion_service.dtos.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {

    private UserProfileResponse userProfile;

    private CompanionPreferenceResponse userPreference;

    private TripResponse trip;

    private List<CandidateProfileResponse> candidates;
}