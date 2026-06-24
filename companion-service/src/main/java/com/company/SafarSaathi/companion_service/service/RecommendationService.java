package com.company.SafarSaathi.companion_service.service;


import com.company.SafarSaathi.companion_service.auth.UserContextHolder;
import com.company.SafarSaathi.companion_service.client.TripServiceClient;
import com.company.SafarSaathi.companion_service.client.UserServiceClient;
import com.company.SafarSaathi.companion_service.dtos.external.TripResponse;
import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import com.company.SafarSaathi.companion_service.dtos.response.RecommendationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendationService {

    private final TripServiceClient tripServiceClient;
    private final UserServiceClient userServiceClient;

    public List<RecommendationResponse> getRecommendations() {

        Long currentUserId =
                UserContextHolder.getCurrentUserId();

        List<TripResponse> publicTrips =
                tripServiceClient.getAllPublicTrips();

        return publicTrips.stream()

                // Don't recommend my own trips
                .filter(trip ->
                        !trip.getUserId().equals(currentUserId)
                )

                .map(trip -> {

                    UserProfileResponse profile =
                            userServiceClient.getUserProfile(
                                    trip.getUserId()
                            );

                    return RecommendationResponse.builder()
                            .userId(profile.getUserId())
                            .tripId(trip.getId())
                            .fullName(profile.getFullName())
                            .destination(trip.getDestination())
                            .matchScore(0) // temporary
                            .travelStyle(profile.getTravelStyle())
                            .tripMode(trip.getModeOfTravel())
                            .build();
                })

                .toList();
    }
}
