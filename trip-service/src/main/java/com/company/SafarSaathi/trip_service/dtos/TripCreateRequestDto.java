package com.company.SafarSaathi.trip_service.dtos;




import com.company.SafarSaathi.trip_service.enums.ModeOfTravel;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripCreateRequestDto {

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    private LocalDateTime endDate;

    @NotNull(message = "Mode of travel is required")
    private ModeOfTravel modeOfTravel;

    @NotNull(message = "Maximum travelers is required")
    @Min(value = 1, message = "Maximum travelers must be at least 1")
    private Integer maxTravelers;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private boolean isPrivate;

    @PositiveOrZero(message = "Estimated cost cannot be negative")
    private Double estimatedCost;
}