package com.company.SafarSaathi.ai_service.planner.rules.Impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecommendationPlanningRule implements PlanningRule {

    @Override
    public boolean matches(ChatRequest request) {

        String query = request.getMessage().toLowerCase();

        return query.contains("recommend")
                || query.contains("travel buddy")
                || query.contains("travel buddies")
                || query.contains("travel companion")
                || query.contains("companion recommendation")
                || query.contains("find companion")
                || query.contains("find buddy")
                || query.contains("match me")
                || query.contains("compatible traveler");
    }

    @Override
    public List<PlannedTool> evaluate(ChatRequest request) {

        return List.of(

                PlannedTool.builder()
                        .toolType(ToolType.TRIP)
                        .order(1)
                        .required(true)
                        .reason("Trip information is required before recommending companions.")
                        .build(),

                PlannedTool.builder()
                        .toolType(ToolType.COMPANION)
                        .order(2)
                        .required(true)
                        .reason("Generate companion recommendations using trip context.")
                        .build()
        );
    }

    @Override
    public int priority() {
        return 100;
    }
}