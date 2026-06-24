package com.company.SafarSaathi.companion_service.service;

import com.company.SafarSaathi.companion_service.auth.UserContextHolder;
import com.company.SafarSaathi.companion_service.client.TripServiceClient;
import com.company.SafarSaathi.companion_service.client.UserServiceClient;
import com.company.SafarSaathi.companion_service.dtos.external.TripResponse;
import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import com.company.SafarSaathi.companion_service.dtos.response.RecommendationResponse;
import com.company.SafarSaathi.companion_service.entity.CompanionPreference;
import com.company.SafarSaathi.companion_service.repository.CompanionPreferenceRepository;
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
    private final CompanionPreferenceRepository preferenceRepository;

    public List<RecommendationResponse> getRecommendations() {

        Long currentUserId =
                UserContextHolder.getCurrentUserId();

        CompanionPreference preference =
                preferenceRepository
                        .findByUserId(currentUserId)
                        .orElse(null);

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

                    UserProfileResponse profile =
                            userServiceClient.getUserProfile(
                                    candidateTrip.getUserId()
                            );

                    int tripScore =
                            calculateScore(
                                    referenceTrip,
                                    candidateTrip
                            );

                    int preferenceScore =
                            calculatePreferenceScore(
                                    preference,
                                    profile,
                                    candidateTrip
                            );

                    int finalScore =
                            tripScore + preferenceScore;

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
                            .matchScore(finalScore)
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

    private int calculatePreferenceScore(
            CompanionPreference preference,
            UserProfileResponse candidateProfile,
            TripResponse candidateTrip
    ) {

        if (preference == null) {
            return 0;
        }

        int score = 0;

        score += calculateAgeScore(
                preference,
                candidateProfile
        );

        score += calculateGenderScore(
                preference,
                candidateProfile
        );

        score += calculateSmokerScore(
                preference,
                candidateProfile
        );

        score += calculateDrinkerScore(
                preference,
                candidateProfile
        );

        score += calculateTripModePreferenceScore(
                preference,
                candidateTrip
        );

        return score;
    }

    private int calculateAgeScore(
            CompanionPreference preference,
            UserProfileResponse profile
    ) {

        if (profile.getAge() == null) {
            return 0;
        }

        Integer min = preference.getPreferredAgeMin();
        Integer max = preference.getPreferredAgeMax();

        if (min == null || max == null) {
            return 0;
        }

        return profile.getAge() >= min
                && profile.getAge() <= max
                ? 10
                : 0;
    }

    private int calculateGenderScore(
            CompanionPreference preference,
            UserProfileResponse profile
    ) {

        if (preference.getPreferredGender() == null
                || profile.getGender() == null) {
            return 0;
        }

        return preference.getPreferredGender()
                .equalsIgnoreCase(
                        profile.getGender()
                )
                ? 5
                : 0;
    }

    private int calculateSmokerScore(
            CompanionPreference preference,
            UserProfileResponse profile
    ) {

        if (preference.getSmokerOk() == null) {
            return 0;
        }

        if (!preference.getSmokerOk()
                && profile.isSmoker()) {
            return 0;
        }

        return 5;
    }

    private int calculateDrinkerScore(
            CompanionPreference preference,
            UserProfileResponse profile
    ) {

        if (preference.getDrinkerOk() == null) {
            return 0;
        }

        if (!preference.getDrinkerOk()
                && profile.isDrinker()) {
            return 0;
        }

        return 5;
    }

    private int calculateTripModePreferenceScore(
            CompanionPreference preference,
            TripResponse candidateTrip
    ) {

        if (preference.getPreferredTripMode() == null
                || candidateTrip.getModeOfTravel() == null) {
            return 0;
        }

        return preference.getPreferredTripMode()
                .equalsIgnoreCase(
                        candidateTrip.getModeOfTravel()
                )
                ? 10
                : 0;
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