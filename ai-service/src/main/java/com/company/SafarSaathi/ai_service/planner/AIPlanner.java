package com.company.SafarSaathi.ai_service.planner;


import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;

public interface AIPlanner {

    ExecutionPlan createPlan(ConversationContext context);

}