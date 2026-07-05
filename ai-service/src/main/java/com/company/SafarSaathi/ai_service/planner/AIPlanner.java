package com.company.SafarSaathi.ai_service.planner;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;

public interface  AIPlanner {

    ExecutionPlan createPlan(ChatRequest request);
}
