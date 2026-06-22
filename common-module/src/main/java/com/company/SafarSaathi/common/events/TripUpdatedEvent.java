package com.company.SafarSaathi.common.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripUpdatedEvent {

    private Long tripId;

    private Long userId;

    private String destination;

    private String origin;
}
