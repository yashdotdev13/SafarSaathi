package com.company.SafarSaathi.ai_service.planner.rules;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuleEvaluator {

    private final PlanningRuleRegistry registry;

    public List<PlannedTool> evaluate(ChatRequest request) {

        List<PlanningRule> rules = registry.getRules()
                .stream()
                .sorted(
                        Comparator.comparingInt(PlanningRule::priority)
                                .reversed()
                )
                .toList();

        for (PlanningRule rule : rules) {

            if (rule.matches(request)) {

                log.info(
                        "Selected planning rule: {} (priority={})",
                        rule.getClass().getSimpleName(),
                        rule.priority()
                );

                return rule.evaluate(request);
            }
        }

        log.info("No planning rule matched.");

        return List.of();
    }
}