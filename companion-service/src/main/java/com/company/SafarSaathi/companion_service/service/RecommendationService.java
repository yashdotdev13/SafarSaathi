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

import java.util.Comparator;
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

        List<TripResponse> myTrips =
                tripServiceClient.getMyTrips();

        if (myTrips.isEmpty()) {
            return List.of();
        }

        TripResponse referenceTrip =
                myTrips.stream()
                        .filter(trip ->
                                "PLANNED".equalsIgnoreCase(
                                        trip.getStatus()
                                )
                        )
                        .findFirst()
                        .orElse(myTrips.getFirst());

        List<TripResponse> publicTrips =
                tripServiceClient.getAllPublicTrips();

        return publicTrips.stream()

                .filter(trip ->
                        !trip.getUserId()
                                .equals(currentUserId)
                )

                .map(candidateTrip -> {

                    int score =
                            calculateScore(
                                    referenceTrip,
                                    candidateTrip
                            );

                    UserProfileResponse profile =
                            userServiceClient.getUserProfile(
                                    candidateTrip.getUserId()
                            );

                    return RecommendationResponse.builder()
                            .userId(profile.getUserId())
                            .tripId(candidateTrip.getId())
                            .fullName(profile.getFullName())
                            .destination(
                                    candidateTrip.getDestination()
                            )
                            .travelStyle(
                                    profile.getTravelStyle()
                            )
                            .tripMode(
                                    candidateTrip.getModeOfTravel()
                            )
                            .matchScore(score)
                            .build();
                })

                .filter(response ->
                        response.getMatchScore() >= 30
                )

                .sorted(
                        Comparator.comparing(
                                RecommendationResponse::getMatchScore
                        ).reversed()
                )

                .toList();
    }

    private int calculateScore(
            TripResponse myTrip,
            TripResponse candidateTrip
    ) {

        int score = 0;

        // Destination Match
        if (myTrip.getDestination() != null
                && candidateTrip.getDestination() != null
                && myTrip.getDestination()
                .equalsIgnoreCase(
                        candidateTrip.getDestination()
                )) {

            score += 40;
        }

        // Same Origin
        if (myTrip.getOrigin() != null
                && candidateTrip.getOrigin() != null
                && myTrip.getOrigin()
                .equalsIgnoreCase(
                        candidateTrip.getOrigin()
                )) {

            score += 10;
        }

        // Same Travel Mode
        if (myTrip.getModeOfTravel() != null
                && candidateTrip.getModeOfTravel() != null
                && myTrip.getModeOfTravel()
                .equalsIgnoreCase(
                        candidateTrip.getModeOfTravel()
                )) {

            score += 20;
        }

        // Date Overlap
        if (hasDateOverlap(myTrip, candidateTrip)) {
            score += 20;
        }

        return score;
    }

    private boolean hasDateOverlap(
            TripResponse myTrip,
            TripResponse candidateTrip
    ) {

        return !myTrip.getEndDate()
                .isBefore(
                        candidateTrip.getStartDate()
                )
                &&
                !candidateTrip.getEndDate()
                        .isBefore(
                                myTrip.getStartDate()
                        );
    }
}