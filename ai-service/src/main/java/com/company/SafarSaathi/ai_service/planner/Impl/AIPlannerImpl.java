package com.company.SafarSaathi.ai_service.planner.Impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionResult;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionService;
import com.company.SafarSaathi.ai_service.intent.IntentType;
import com.company.SafarSaathi.ai_service.planner.AIPlanner;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIPlannerImpl implements AIPlanner {

    private final IntentDetectionService intentDetectionService;

    @Override
    public ExecutionPlan createPlan(ChatRequest request) {

        log.info("Creating execution plan.");

        IntentDetectionResult intent =
                intentDetectionService.detectIntent(
                        request.getMessage()
                );

        PlannedTool plannedTool = mapIntentToTool(
                intent.getIntentType()
        );

        if (plannedTool == null) {

            return ExecutionPlan.builder()
                    .strategy(ExecutionStrategy.SEQUENTIAL)
                    .tools(List.of())
                    .build();
        }

        return ExecutionPlan.builder()
                .strategy(ExecutionStrategy.SEQUENTIAL)
                .tools(List.of(plannedTool))
                .build();
    }

    private PlannedTool mapIntentToTool(
            IntentType intentType
    ) {

        return switch (intentType) {

            case TRIP -> PlannedTool.builder()
                    .toolType(ToolType.TRIP)
                    .order(1)
                    .build();

            case USER -> PlannedTool.builder()
                    .toolType(ToolType.USER)
                    .order(1)
                    .build();

            case COMPANION -> PlannedTool.builder()
                    .toolType(ToolType.COMPANION)
                    .order(1)
                    .build();

            default -> null;
        };
    }
}