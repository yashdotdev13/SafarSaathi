package com.company.SafarSaathi.ai_service.planner.service;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;

public interface PlannerEngine {

    ExecutionPlan createExecutionPlan(ConversationContext context);
}
