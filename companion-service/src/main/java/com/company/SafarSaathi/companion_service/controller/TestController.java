package com.company.SafarSaathi.companion_service.controller;

import com.company.SafarSaathi.companion_service.client.TripServiceClient;
import com.company.SafarSaathi.companion_service.dtos.external.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companions/test")
public class TestController {

    private final TripServiceClient tripServiceClient;

    @GetMapping("/my-trips")
    public List<TripResponse> getMyTrips() {
        return tripServiceClient.getMyTrips();
    }
}