package com.company.SafarSaathi.ai_service.planner.executor.Impl;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
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
public class PlanExecutorImpl implements PlanExecutor {

    private final ToolExecutor toolExecutor;

    @Override
    public List<ToolResponse> execute(
            ExecutionPlan executionPlan,
            ChatRequest request
    ) {



        log.info(
                "Executing plan with {} tool(s).",
                executionPlan.getTools().size()
        );

        List<ToolResponse> responses = new ArrayList<>();

        for (PlannedTool plannedTool : executionPlan.getTools()) {

            log.info(
                    "Executing tool: {}",
                    plannedTool.getToolType()
            );

            ToolRequest toolRequest = ToolRequest.builder()
                    .toolType(plannedTool.getToolType())
                    .conversationId(request.getConversationId())
                    .query(request.getMessage())
                    .build();

            ToolResponse response =
                    toolExecutor.execute(toolRequest);

            responses.add(response);

            log.info(
                    "Completed tool: {}",
                    plannedTool.getToolType()
            );
        }

        log.info(
                "Successfully executed {} tool(s).",
                responses.size()
        );

        return responses;
    }
}