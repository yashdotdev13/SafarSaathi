package com.company.SafarSaathi.common.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripCreatedEvent {

    private Long tripId;

    private Long userId;

    private String origin;
    private String destination;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String modeOTravel;
}
