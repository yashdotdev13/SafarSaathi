package com.company.SafarSaathi.ai_service.planner.rules.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummaryPlanningRule implements PlanningRule {

    @Override
    public boolean matches(ConversationContext context) {

        String query = context
                .getChatRequest()
                .getMessage()
                .toLowerCase();

        return query.contains("summary")
                || query.contains("summarize")
                || query.contains("overview")
                || query.contains("everything")
                || query.contains("dashboard")
                || query.contains("complete information")
                || query.contains("all my information");
    }

    @Override
    public List<PlannedTool> evaluate(ConversationContext context) {

        return List.of(

                PlannedTool.builder()
                        .toolType(ToolType.USER)
                        .order(1)
                        .required(true)
                        .reason("Fetch user profile.")
                        .build(),

                PlannedTool.builder()
                        .toolType(ToolType.TRIP)
                        .order(2)
                        .required(true)
                        .reason("Fetch user trips.")
                        .build(),

                PlannedTool.builder()
                        .toolType(ToolType.COMPANION)
                        .order(3)
                        .required(true)
                        .reason("Fetch companion recommendations.")
                        .build()
        );
    }

    @Override
    public int priority() {
        return 200;
    }
}