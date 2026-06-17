package com.company.SafarSaathi.companion_service.dtos.request;


import com.company.SafarSaathi.companion_service.dtos.CandidateProfile;
import com.company.SafarSaathi.companion_service.dtos.CompanionPreferenceDto;
import com.company.SafarSaathi.companion_service.dtos.TripDto;
import com.company.SafarSaathi.companion_service.dtos.UserProfileCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchRequest {
    private UserProfileCreateRequest userProfileCreateRequest;
    private CompanionPreferenceDto userPreference;
    private TripDto trip;

    private List<CandidateProfile> candidates;
}
