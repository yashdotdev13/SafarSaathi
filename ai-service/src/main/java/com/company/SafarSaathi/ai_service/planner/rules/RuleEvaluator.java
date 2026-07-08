package com.company.SafarSaathi.ai_service.planner.rules;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuleEvaluator {

    private final PlanningRuleRegistry registry;

    public List<PlannedTool> evaluate(
            ConversationContext context
    ) {

        List<PlanningRule> rules = registry.getRules()
                .stream()
                .sorted(
                        Comparator.comparingInt(PlanningRule::priority)
                                .reversed()
                )
                .toList();

        for (PlanningRule rule : rules) {

            if (rule.matches(context)) {

                log.info(
                        "Selected planning rule: {} (priority={})",
                        rule.getClass().getSimpleName(),
                        rule.priority()
                );

                return rule.evaluate(context);
            }
        }

        log.info("No planning rule matched.");

        return List.of();
    }
}