package com.company.SafarSaathi.ai_service.planner.validator.Impl;


import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.exception.InvalidExecutionPlanException;
import com.company.SafarSaathi.ai_service.planner.validator.ExecutionPlanValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ExecutionPlanValidatorImpl implements ExecutionPlanValidator {


    @Override
    public void validate(ExecutionPlan executionPlan) {

        log.info("Validating execution plan.");

        if (executionPlan == null) {
            throw new InvalidExecutionPlanException(
                    "Execution plan cannot be null."
            );
        }

        if (executionPlan.getStrategy() == null) {
            throw new InvalidExecutionPlanException(
                    "Execution strategy is required."
            );
        }

        if (executionPlan.getTools() == null) {
            throw new InvalidExecutionPlanException(
                    "Tools list cannot be null."
            );
        }

        Set<Integer> orders = new HashSet<>();

        for (PlannedTool tool : executionPlan.getTools()) {

            validateTool(tool, orders);

        }

        log.info("Execution plan validated successfully.");
    }

    private void validateTool(
            PlannedTool tool,
            Set<Integer> orders
    ) {

        if (tool.getToolType() == null) {
            throw new InvalidExecutionPlanException(
                    "Tool type cannot be null."
            );
        }

        if (tool.getOrder() == null) {
            throw new InvalidExecutionPlanException(
                    "Execution order cannot be null."
            );
        }

        if (tool.getOrder() <= 0) {
            throw new InvalidExecutionPlanException(
                    "Execution order must be greater than zero."
            );
        }

        if (!orders.add(tool.getOrder())) {
            throw new InvalidExecutionPlanException(
                    "Duplicate execution order found: " + tool.getOrder()
            );
        }

        if (tool.getReason() == null || tool.getReason().isBlank()) {
            throw new InvalidExecutionPlanException(
                    "Tool reason cannot be empty."
            );
        }
    }
}
