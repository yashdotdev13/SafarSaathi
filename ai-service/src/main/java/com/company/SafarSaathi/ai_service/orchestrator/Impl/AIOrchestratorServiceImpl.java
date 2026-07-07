package com.company.SafarSaathi.ai_service.orchestrator.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.resolver.ConversationContextResolver;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.orchestrator.AIOrchestratorService;
import com.company.SafarSaathi.ai_service.planner.AIPlanner;
import com.company.SafarSaathi.ai_service.planner.context.ContextMerger;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.executor.PlanExecutor;
import com.company.SafarSaathi.ai_service.service.AIChatService;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIOrchestratorServiceImpl implements AIOrchestratorService {

    private final ConversationContextResolver conversationContextResolver;
    private final AIPlanner aiPlanner;
    private final PlanExecutor planExecutor;
    private final ContextMerger contextMerger;
    private final PromptEnrichmentService promptEnrichmentService;
    private final AIChatService aiChatService;

    @Override
    public ChatResponse process(ChatRequest request) {

        log.info("Processing request through AI Orchestrator.");

        ConversationContext context =
                conversationContextResolver.resolve(request);

        ExecutionPlan executionPlan =
                aiPlanner.createPlan(context);

        if (executionPlan.getTools().isEmpty()) {

            log.info("No planning rule matched. Routing to General Chat.");

            return handleGeneralChat(request);
        }

        return handleToolRequest(
                request,
                executionPlan
        );
    }

    private ChatResponse handleGeneralChat(
            ChatRequest request
    ) {

        log.info("Handling General Chat.");

        return aiChatService.chat(request);
    }

    private ChatResponse handleToolRequest(
            ChatRequest request,
            ExecutionPlan executionPlan
    ) {

        log.info(
                "Executing plan with {} tool(s).",
                executionPlan.getTools().size()
        );

        List<ToolResponse> responses =
                planExecutor.execute(
                        executionPlan,
                        request
                );

        String mergedContext =
                contextMerger.merge(responses);

        String enrichedPrompt =
                promptEnrichmentService.enrich(
                        request,
                        mergedContext
                );

        return aiChatService.chat(
                request,
                enrichedPrompt
        );
    }
}