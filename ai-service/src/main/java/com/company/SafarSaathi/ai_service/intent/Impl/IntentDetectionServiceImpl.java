package com.company.SafarSaathi.ai_service.intent.Impl;

import com.company.SafarSaathi.ai_service.intent.IntentDetectionResult;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionService;
import com.company.SafarSaathi.ai_service.intent.IntentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
@Slf4j
public class IntentDetectionServiceImpl
        implements IntentDetectionService {

    @Override
    public IntentDetectionResult detectIntent(String userQuery) {

        log.info("Detecting intent for query: {}", userQuery);

        String query = userQuery.toLowerCase(Locale.ROOT);

        // Highest Priority
        if (containsCompanionKeywords(query)) {
            return IntentDetectionResult.builder()
                    .intentType(IntentType.COMPANION)
                    .confidence(0.95)
                    .parameters(Map.of())
                    .build();
        }

        if (containsTripKeywords(query)) {
            return IntentDetectionResult.builder()
                    .intentType(IntentType.TRIP)
                    .confidence(0.90)
                    .parameters(Map.of())
                    .build();
        }

        if (containsUserKeywords(query)) {
            return IntentDetectionResult.builder()
                    .intentType(IntentType.USER)
                    .confidence(0.90)
                    .parameters(Map.of())
                    .build();
        }

        return IntentDetectionResult.builder()
                .intentType(IntentType.GENERAL_CHAT)
                .confidence(0.60)
                .parameters(Map.of())
                .build();
    }

    private boolean containsTripKeywords(String query) {

        return query.contains("trip")
                || query.contains("destination")
                || query.contains("itinerary")
                || query.contains("vacation")
                || query.contains("journey")
                || query.contains("my trips")
                || query.contains("upcoming trip")
                || query.contains("completed trip");
    }

    private boolean containsCompanionKeywords(String query) {

        return query.contains("companion")
                || query.contains("travel buddy")
                || query.contains("buddy")
                || query.contains("travel partner")
                || query.contains("partner")
                || query.contains("recommend companion")
                || query.contains("recommendation")
                || query.contains("match")
                || query.contains("matches")
                || query.contains("matching")
                || query.contains("compatible")
                || query.contains("who can travel with me")
                || query.contains("find companion")
                || query.contains("find me a companion");
    }

    private boolean containsUserKeywords(String query) {

        return query.contains("profile")
                || query.contains("account")
                || query.contains("my details")
                || query.contains("preferences")
                || query.contains("about me")
                || query.contains("who am i")
                || query.contains("tell me about myself")
                || query.contains("myself")
                || query.contains("my information")
                || query.contains("my profile")
                || query.contains("my info")
                || query.contains("my age")
                || query.contains("my email")
                || query.contains("my phone")
                || query.contains("my city")
                || query.contains("my country")
                || query.contains("my lifestyle")
                || query.contains("my travel style")
                || query.contains("am i a smoker")
                || query.contains("am i a drinker");
    }

}