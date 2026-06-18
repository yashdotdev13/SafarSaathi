package com.company.SafarSaathi.companion_service.dtos.response;

import com.company.SafarSaathi.companion_service.enums.ModeOfTravel;
import com.company.SafarSaathi.companion_service.enums.TripStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripResponse {

    private Long id;

    private String destination;

    private String origin;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private ModeOfTravel modeOfTravel;

    private Integer maxTravelers;

    private Integer currentTravelers;

    private String description;

    private boolean isPrivate;

    private Double estimatedCost;

    private TripStatus status;

    private Long userId;
}