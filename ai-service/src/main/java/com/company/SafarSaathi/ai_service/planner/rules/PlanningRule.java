package com.company.SafarSaathi.ai_service.planner.rules;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;

import java.util.List;

public interface PlanningRule {

    /**
     * Determines whether this rule should participate
     * in planning for the given conversation context.
     */
    boolean matches(ConversationContext context);

    /**
     * Returns one or more planned tools contributed
     * by this rule.
     */
    List<PlannedTool> evaluate(ConversationContext context);

    /**
     * Rule priority.
     */
    int priority();
}