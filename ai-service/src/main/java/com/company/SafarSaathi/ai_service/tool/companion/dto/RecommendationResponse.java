package com.company.SafarSaathi.ai_service.tool.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationResponse {

    private Long userId;
    private Long tripId;

    private String fullName;

    private String destination;

    private String matchScore;
    private String travelStyle;

    private String tripMode;
}
