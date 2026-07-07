package com.company.SafarSaathi.ai_service.planner.rules.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPlanningRule implements PlanningRule {

    @Override
    public boolean matches(ConversationContext context) {

        String query = context
                .getChatRequest()
                .getMessage()
                .toLowerCase();

        return query.contains("profile")
                || query.contains("myself")
                || query.contains("who am i")
                || query.contains("my details")
                || query.contains("my information")
                || query.contains("preferences")
                || query.contains("account");
    }

    @Override
    public List<PlannedTool> evaluate(ConversationContext context) {

        return List.of(
                PlannedTool.builder()
                        .toolType(ToolType.USER)
                        .order(1)
                        .required(true)
                        .reason("User profile information is required.")
                        .build()
        );
    }

    @Override
    public int priority() {
        return 10;
    }
}