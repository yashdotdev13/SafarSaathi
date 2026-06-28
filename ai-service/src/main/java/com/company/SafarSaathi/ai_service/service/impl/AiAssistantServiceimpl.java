package com.company.SafarSaathi.ai_service.service.impl;


import com.company.SafarSaathi.ai_service.auth.UserContextHolder;
import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.conversation.enums.MessageRole;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.service.AiAssistantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiAssistantServiceimpl implements AiAssistantService {

    private final ChatClient chatClient;
    private final PromptBuilderService promptBuilderService;
    private final ConversationService conversationService;

    @Override
    public ChatResponse chat(ChatRequest request) {

        log.info("Received AI chat request.");

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
                chatClient.prompt()
                        .user(prompt)
                        .call()
                        .content();

        conversationService.saveMessage(
                conversation,
                MessageRole.ASSISTANT,
                aiResponse
        );

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
}