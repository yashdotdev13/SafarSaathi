package com.company.SafarSaathi.ai_service.tool.trip.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponse {


    private Long id;
    private Long userId;
    private String title;
    private String destination;
    private String origin;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String modeOfTravel;
    private String status;
    private Boolean isPublic;
}
