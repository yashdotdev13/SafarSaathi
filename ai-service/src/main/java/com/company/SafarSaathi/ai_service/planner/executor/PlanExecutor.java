package com.company.SafarSaathi.ai_service.planner.executor;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;

import java.util.List;

public interface PlanExecutor {

    List<ToolResponse> execute(
            ExecutionPlan executionPlan,
            ChatRequest request
    );

}