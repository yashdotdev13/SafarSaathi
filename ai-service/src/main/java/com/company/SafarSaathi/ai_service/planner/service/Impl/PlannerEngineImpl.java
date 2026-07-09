package com.company.SafarSaathi.ai_service.planner.service.Impl;



import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;
import com.company.SafarSaathi.ai_service.planner.llm.LLMPlanner;
import com.company.SafarSaathi.ai_service.planner.parser.PlannerResponseParser;
import com.company.SafarSaathi.ai_service.planner.prompt.PlannerPromptBuilder;
import com.company.SafarSaathi.ai_service.planner.rules.RuleEvaluator;
import com.company.SafarSaathi.ai_service.planner.service.PlannerEngine;
import com.company.SafarSaathi.ai_service.planner.validator.ExecutionPlanValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannerEngineImpl implements PlannerEngine {


    private final PlannerPromptBuilder plannerPromptBuilder;
    private final LLMPlanner llmPlanner;
    private final PlannerResponseParser plannerResponseParser;
    private final ExecutionPlanValidator executionPlanValidator;
    private final RuleEvaluator ruleEvaluator;

    @Override
    public ExecutionPlan createExecutionPlan(
            ConversationContext context
    ) {

        try {

            log.info("Creating execution plan using LLM.");

            String plannerPrompt =
                    plannerPromptBuilder.buildPrompt(context);

            String plannerResponse =
                    llmPlanner.generatePlan(plannerPrompt);

            ExecutionPlan executionPlan =
                    plannerResponseParser.parse(plannerResponse);

            executionPlanValidator.validate(executionPlan);

            log.info("LLM execution plan created successfully.");

            return executionPlan;

        } catch (Exception ex) {

            log.warn(
                    "LLM planning failed. Falling back to rule-based planner.",
                    ex
            );
            List<PlannedTool> plannedTools =
                    ruleEvaluator.evaluate(context);

            return ExecutionPlan.builder()
                    .strategy(ExecutionStrategy.SEQUENTIAL)
                    .tools(plannedTools)
                    .build();
        }
    }
}