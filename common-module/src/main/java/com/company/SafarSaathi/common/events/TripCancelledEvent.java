package com.company.SafarSaathi.common.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripCancelledEvent {


    private Long tripId;

    private Long userId;
}
