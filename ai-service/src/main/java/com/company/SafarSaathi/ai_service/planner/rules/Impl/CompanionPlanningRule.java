package com.company.SafarSaathi.ai_service.planner.rules.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanionPlanningRule implements PlanningRule {

    @Override
    public boolean matches(ConversationContext context) {

        String query = context
                .getChatRequest()
                .getMessage()
                .toLowerCase();

        return query.contains("companion")
                || query.contains("travel buddy")
                || query.contains("buddy")
                || query.contains("travel partner")
                || query.contains("partner")
                || query.contains("matching")
                || query.contains("compatible");
    }

    @Override
    public List<PlannedTool> evaluate(ConversationContext context) {

        return List.of(
                PlannedTool.builder()
                        .toolType(ToolType.COMPANION)
                        .order(1)
                        .required(true)
                        .reason("Companion recommendations are required.")
                        .build()
        );
    }

    @Override
    public int priority() {
        return 10;
    }
}