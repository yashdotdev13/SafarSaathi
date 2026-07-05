package com.company.SafarSaathi.ai_service.planner.executor.Impl;

import com.company.SafarSaathi.ai_service.planner.context.ContextMerger;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.dto.PlannedTool;
import com.company.SafarSaathi.ai_service.planner.executor.PlanExecutor;
import com.company.SafarSaathi.ai_service.tool.ToolExecutor;
import com.company.SafarSaathi.ai_service.tool.ToolRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanExecutorImpl
        implements PlanExecutor {

    private final ToolExecutor toolExecutor;

    private final ContextMerger contextMerger;

    @Override
    public String execute(
            ExecutionPlan executionPlan,
            String conversationId,
            String query
    ) {

        List<ToolResponse> responses = new ArrayList<>();

        for (PlannedTool plannedTool : executionPlan.getTools()) {

            ToolRequest toolRequest =
                    ToolRequest.builder()
                            .toolType(plannedTool.getToolType())
                            .conversationId(conversationId)
                            .query(query)
                            .build();

            responses.add(
                    toolExecutor.execute(toolRequest)
            );
        }

        return contextMerger.merge(responses);
    }
}