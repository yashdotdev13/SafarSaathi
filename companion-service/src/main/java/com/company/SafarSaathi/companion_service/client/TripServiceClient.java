package com.company.SafarSaathi.companion_service.client;


import com.company.SafarSaathi.companion_service.config.FeignConfig;
import com.company.SafarSaathi.companion_service.dtos.external.TripResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "TRIP-SERVICE",
        configuration = FeignConfig.class
)
public interface TripServiceClient {

    @GetMapping("/core/public")
    List<TripResponse> getAllPublicTrips();
}
