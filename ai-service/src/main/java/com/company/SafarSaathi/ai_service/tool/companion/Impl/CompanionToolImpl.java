package com.company.SafarSaathi.ai_service.tool.companion.Impl;

import com.company.SafarSaathi.ai_service.tool.ToolException;
import com.company.SafarSaathi.ai_service.tool.ToolRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import com.company.SafarSaathi.ai_service.tool.companion.CompanionTool;
import com.company.SafarSaathi.ai_service.tool.companion.dto.RecommendationResponse;
import com.company.SafarSaathi.ai_service.tool.companion.feign.RecommendationServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompanionToolImpl implements CompanionTool {

    private final RecommendationServiceClient recommendationServiceClient;

    @Override
    public ToolType getToolType() {
        return ToolType.COMPANION;
    }

    @Override
    public ToolResponse execute(ToolRequest request) {

        log.info("Executing Companion Tool.");

        try {

            List<RecommendationResponse> recommendations =
                    recommendationServiceClient.getRecommendations();

            return ToolResponse.builder()
                    .toolType(ToolType.COMPANION)
                    .success(true)
                    .message("Companion recommendations fetched successfully.")
                    .data(recommendations)
                    .build();

        } catch (Exception ex) {

            log.error(
                    "Failed to fetch companion recommendations.",
                    ex
            );

            throw new ToolException(
                    "Unable to fetch companion recommendations.",
                    ex
            );
        }
    }
}