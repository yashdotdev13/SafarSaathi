package com.company.SafarSaathi.ai_service.tool.trip.client;


import com.company.SafarSaathi.ai_service.tool.trip.dto.TripResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "trip-service"
)
public interface TripServiceClient {

    @GetMapping("/trip/core/me")
    List<TripResponse> getMyTrips();

}
