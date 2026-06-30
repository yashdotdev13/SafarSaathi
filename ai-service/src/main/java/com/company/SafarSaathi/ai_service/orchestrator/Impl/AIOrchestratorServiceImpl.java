package com.company.SafarSaathi.ai_service.orchestrator.Impl;


import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionResult;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionService;
import com.company.SafarSaathi.ai_service.orchestrator.AIOrchestratorService;
import com.company.SafarSaathi.ai_service.service.AIChatService;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import com.company.SafarSaathi.ai_service.tool.ToolExecutor;
import com.company.SafarSaathi.ai_service.tool.ToolRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIOrchestratorServiceImpl implements AIOrchestratorService {

    private final IntentDetectionService intentDetectionService;
    private final ToolExecutor toolExecutor;
    private final AIChatService aiChatService;
    private final PromptEnrichmentService promptEnrichmentService;
    private final ConversationService conversationService;


    @Override
    public ChatResponse process(ChatRequest request) {

        log.info("Processing request through AI orchestrator");

        IntentDetectionResult intent = intentDetectionService.detectIntent(
                request.getMessage());


        log.info("Detected intent: {}", intent.getIntentType());

        switch(intent.getIntentType()) {
            case GENERAL_CHAT:
                return handleGeneralChat(request);

            case TRIP:
            case USER:
            case COMPANION:
                return handleToolRequest(request, intent);

            default: throw new IllegalStateException("Unsupported intent: "+ intent.getIntentType());
        }
    }


    private ChatResponse handleGeneralChat(
            ChatRequest request
    ) {

        log.info("Handling general chat.");

        return aiChatService.chat(request);

    }


    private ChatResponse handleToolRequest(
            ChatRequest request,
            IntentDetectionResult intent
    ) {

        log.info(
                "Handling tool request for intent: {}",
                intent.getIntentType()
        );

        ToolRequest toolRequest =
                ToolRequest.builder()
                        .toolType(
                                ToolType.valueOf(
                                        intent.getIntentType().name()
                                )
                        )
                        .conversationId(
                                request.getConversationId()
                        )
                        .query(
                                request.getMessage()
                        )
                        .build();

        ToolResponse toolResponse =
                toolExecutor.execute(toolRequest);

        String enrichedPrompt =
                promptEnrichmentService.enrich(
                        request,
                        toolResponse
                );

        return aiChatService.chat(
                request,
                enrichedPrompt
        );
    }
}
