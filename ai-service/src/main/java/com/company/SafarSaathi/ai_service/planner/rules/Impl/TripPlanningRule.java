package com.company.SafarSaathi.ai_service.planner.rules.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.rules.PlanningRule;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripPlanningRule implements PlanningRule {

    @Override
    public boolean matches(ConversationContext context) {

        String query = context
                .getChatRequest()
                .getMessage()
                .toLowerCase();

        return query.contains("trip")
                || query.contains("journey")
                || query.contains("destination")
                || query.contains("vacation")
                || query.contains("travel plan")
                || query.contains("itinerary")
                || query.contains("upcoming trip");
    }

    @Override
    public List<PlannedTool> evaluate(ConversationContext context) {

        return List.of(
                PlannedTool.builder()
                        .toolType(ToolType.TRIP)
                        .order(1)
                        .required(true)
                        .reason("Trip information is required.")
                        .build()
        );
    }

    @Override
    public int priority() {
        return 10;
    }
}