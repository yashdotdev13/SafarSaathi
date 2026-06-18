package com.company.SafarSaathi.companion_service.dtos.request;



import com.company.SafarSaathi.companion_service.dtos.response.CandidateProfileResponse;
import com.company.SafarSaathi.companion_service.dtos.response.CompanionPreferenceResponse;
import com.company.SafarSaathi.companion_service.dtos.response.TripResponse;
import com.company.SafarSaathi.companion_service.dtos.response.UserProfileResponse;
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