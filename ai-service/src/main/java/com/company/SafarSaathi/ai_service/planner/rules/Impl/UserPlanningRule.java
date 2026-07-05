package com.company.SafarSaathi.ai_service.planner.rules.Impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPlanningRule implements PlanningRule {


    @Override
    public boolean matches(ChatRequest request) {
        String query = request.getMessage().toLowerCase();

        return query.contains("profile")
                || query.contains("myself")
                || query.contains("who am i")
                || query.contains("my details")
                || query.contains("my information")
                || query.contains("preferences")
                || query.contains("account");
    }

    @Override
    public List<PlannedTool> evaluate(ChatRequest request) {

        return List.of(
                PlannedTool.builder()
                        .toolType(ToolType.USER)
                        .order(1)
                        .required(true)
                        .reason("User profile information is required.")
                        .build()
        );
    }
}
