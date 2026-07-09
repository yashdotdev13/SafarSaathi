package com.company.SafarSaathi.ai_service.planner.Impl;



import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.AIPlanner;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.service.PlannerEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIPlannerImpl
        implements AIPlanner {

    private final PlannerEngine planningEngine;

    @Override
    public ExecutionPlan createPlan(
            ConversationContext context
    ) {

        log.info("Delegating planning request to Planning Engine.");
        return planningEngine.createExecutionPlan(context);

    }
}