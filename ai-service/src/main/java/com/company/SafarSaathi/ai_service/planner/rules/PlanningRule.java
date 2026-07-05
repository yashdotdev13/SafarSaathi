package com.company.SafarSaathi.ai_service.planner.rules;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;

import java.util.List;

public interface PlanningRule {

    /**
     * Determines whether this rule should participate
     * in planning for the given request.
     */
    boolean matches(ChatRequest request);

    /**
     * Returns one or more planned tools contributed
     * by this rule.
     */

    List<PlannedTool> evaluate(ChatRequest request);


    int priority();
}
