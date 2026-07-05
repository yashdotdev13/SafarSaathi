package com.company.SafarSaathi.ai_service.planner.rules;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class PlanningRuleRegistry {

    private final List<PlanningRule> rules;

    public PlanningRuleRegistry(
            List<PlanningRule> rules
    ) {
        this.rules = rules;
    }
}
