package com.company.SafarSaathi.ai_service.service.impl;

import com.company.SafarSaathi.ai_service.auth.UserContextHolder;
import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.resolver.ConversationContextResolver;
import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.conversation.enums.MessageRole;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.memory.engine.MemoryEngine;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.service.AIChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.company.SafarSaathi.ai_service.auth.UserContextHolder.getCurrentUserId;

@Service
@RequiredArgsConstructor
@Slf4j
public class AIChatServiceImpl implements AIChatService {

    private final ChatClient chatClient;
    private final PromptBuilderService promptBuilderService;
    private final ConversationService conversationService;
    private final ConversationContextResolver conversationContextResolver;
    private final MemoryEngine memoryEngine;

    @Override
    public ChatResponse chat(ChatRequest request) {

        log.info("Received AI chat request.");

        Long userId = getCurrentUserId();

        Conversation conversation =
                conversationService.getOrCreateConversation(
                        userId,
                        request.getConversationId()
                );

        conversationService.saveMessage(
                conversation,
                MessageRole.USER,
                request.getMessage()
        );

        List<ConversationMessage> history =
                conversationService.getConversationHistory(
                        conversation
                );

        String prompt =
                promptBuilderService.buildPrompt(
                        PromptType.CHAT,
                        request,
                        history
                );

        log.debug(
                "Generated prompt with {} conversation messages.",
                history.size()
        );

        String aiResponse =
                generateResponse(prompt);

        conversationService.saveMessage(
                conversation,
                MessageRole.ASSISTANT,
                aiResponse
        );

        ConversationContext context =
                conversationContextResolver.resolve(request);

        memoryEngine.extractAndPersistMemories(context);

        log.info(
                "AI response generated successfully. conversationId={}",
                conversation.getConversationId()
        );

        return ChatResponse.builder()
                .conversationId(
                        conversation.getConversationId().toString()
                )
                .response(aiResponse)
                .build();
    }

    @Override
    public ChatResponse chat(ChatRequest request, String prompt) {

        log.info("Processing AI chat using enriched prompt.");

        Long userId = UserContextHolder.getCurrentUserId();

        Conversation conversation =
                conversationService.getOrCreateConversation(
                        userId,
                        request.getConversationId()
                );

        conversationService.saveMessage(
                conversation,
                MessageRole.USER,
                request.getMessage()
        );

        String aiResponse = generateResponse(prompt);

        conversationService.saveMessage(
                conversation,
                MessageRole.ASSISTANT,
                aiResponse
        );

        ConversationContext context =
                conversationContextResolver.resolve(request);

        memoryEngine.extractAndPersistMemories(context);

        return ChatResponse.builder()
                .conversationId(
                        conversation.getConversationId().toString()
                )
                .response(aiResponse)
                .build();
    }

    @Override
    public String generateResponse(String prompt) {

        log.info("Sending enriched prompt to Gemini");

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}