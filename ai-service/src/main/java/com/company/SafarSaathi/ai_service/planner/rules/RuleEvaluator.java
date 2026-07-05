package com.company.SafarSaathi.ai_service.planner.rules;



import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RuleEvaluator {

    private final PlanningRuleRegistry registry;

    public List<PlannedTool> evaluate(
            ChatRequest request
    ) {

        List<PlannedTool> plannedTools =
                new ArrayList<>();

        for (PlanningRule rule : registry.getRules()) {

            if (rule.matches(request)) {

                log.info(
                        "Matched planning rule: {}",
                        rule.getClass().getSimpleName()
                );

                plannedTools.addAll(
                        rule.evaluate(request)
                );
            }
        }

        return plannedTools;
    }
}