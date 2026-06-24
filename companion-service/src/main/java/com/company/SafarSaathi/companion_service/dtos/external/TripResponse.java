package com.company.SafarSaathi.companion_service.dtos.external;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripResponse {

    private Long id;
    private String destination;

    private String origin;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String modeOfTravel;

    private Integer maxTravelers;

    private Integer currentTravelers;

    private String description;

    private Double estimatedCost;

    private String status;

    private Long userId;
}
