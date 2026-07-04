package com.company.SafarSaathi.ai_service.tool.trip.Impl;


import com.company.SafarSaathi.ai_service.tool.ToolException;
import com.company.SafarSaathi.ai_service.tool.ToolRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import com.company.SafarSaathi.ai_service.tool.trip.TripTool;
import com.company.SafarSaathi.ai_service.tool.trip.client.TripServiceClient;
import com.company.SafarSaathi.ai_service.tool.trip.dto.TripResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TripToolImpl implements TripTool {

    private final TripServiceClient tripServiceClient;


    @Override
    public ToolType getToolType() {
        return ToolType.TRIP;
    }

    @Override
    public ToolResponse execute(ToolRequest request) {

        log.info("Executing Trip Tool");

        try {

            List<TripResponse> trips =
                    tripServiceClient.getMyTrips();

            return ToolResponse.builder()
                    .toolType(ToolType.TRIP)
                    .success(true)
                    .message("Trips fetched successfully.")
                    .data(trips)
                    .build();

        } catch (Exception ex) {

            log.error(
                    "Failed to fetch trips.",
                    ex
            );

            throw new ToolException(
                    "Unable to fetch trips.",
                    ex
            );
        }
    }
}
