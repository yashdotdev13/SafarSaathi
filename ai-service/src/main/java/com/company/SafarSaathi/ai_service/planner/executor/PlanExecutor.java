package com.company.SafarSaathi.ai_service.planner.executor;

import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;

public interface PlanExecutor {

    String execute(ExecutionPlan executionPlan, String conversationId,
                   String query);
}
