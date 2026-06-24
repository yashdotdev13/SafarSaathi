package com.company.SafarSaathi.companion_service.controller;

import com.company.SafarSaathi.companion_service.dtos.response.RecommendationResponse;
import com.company.SafarSaathi.companion_service.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companions/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping
    public List<RecommendationResponse> getRecommendations() {
        return recommendationService.getRecommendations();
    }
}