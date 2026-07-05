package com.company.SafarSaathi.ai_service.tool.companion.feign;


import com.company.SafarSaathi.ai_service.tool.companion.dto.RecommendationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "companion-service")
public interface RecommendationServiceClient {

    @GetMapping("/companions/recommendations")
    List<RecommendationResponse> getRecommendations();

}