package com.company.SafarSaathi.ai_service.planner.Impl;


import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.AIPlanner;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;
import com.company.SafarSaathi.ai_service.planner.rules.RuleEvaluator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIPlannerImpl implements AIPlanner {

    private final RuleEvaluator ruleEvaluator;

    @Override
    public ExecutionPlan createPlan(
            ConversationContext context
    ) {

        log.info("Creating execution plan.");

        List<PlannedTool> plannedTools =
                ruleEvaluator.evaluate(context);

        ExecutionPlan executionPlan = ExecutionPlan.builder()
                .strategy(ExecutionStrategy.SEQUENTIAL)
                .tools(plannedTools)
                .build();

        log.info(
                "Execution plan created with {} tool(s).",
                plannedTools.size()
        );

        return executionPlan;
    }
}