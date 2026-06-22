package com.company.SafarSaathi.trip_service.dtos;


import com.company.SafarSaathi.trip_service.enums.ModeOfTravel;
import com.company.SafarSaathi.trip_service.enums.TripStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripUpdateRequestDto {

    private String destination;
    private String origin;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ModeOfTravel modeOfTravel;

    @Min(value = 1, message = "Maximum travelers must be at least 1")
    private Integer maxTravelers;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @PositiveOrZero(message = "Estimated cost cannot be negative")
    private Double estimatedCost;


    private boolean isPrivate;

    private TripStatus status;
}
