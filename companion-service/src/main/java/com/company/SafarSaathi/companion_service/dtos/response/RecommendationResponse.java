package com.company.SafarSaathi.companion_service.dtos.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommendationResponse {

    private Long userId;

    private Long tripId;

    private String fullName;

    private String destination;

    private Integer matchScore;

    private String travelStyle;

    private String tripMode;
}
