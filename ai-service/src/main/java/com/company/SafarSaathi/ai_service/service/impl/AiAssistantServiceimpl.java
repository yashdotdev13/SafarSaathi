package com.company.SafarSaathi.ai_service.service.impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.service.AiAssistantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiAssistantServiceimpl implements AiAssistantService {

    private final ChatClient chatClient;
    private final PromptBuilderService promptBuilderService;

    @Override
    public ChatResponse chat(ChatRequest request) {

        log.info("Received AI chat request.");

        String conversationId = resolveConversationId(
                request.getConversationId()
        );

        String prompt =
                promptBuilderService.buildPrompt(
                        PromptType.CHAT,
                        request
                );

        String aiResponse =
                chatClient.prompt()
                        .user(prompt)
                        .call()
                        .content();

        log.info("AI response generated successfully.");

        return ChatResponse.builder()
                .conversationId(conversationId)
                .response(aiResponse)
                .build();
    }

    private String resolveConversationId(
            String conversationId
    ) {

        if (conversationId == null
                || conversationId.isBlank()) {

            return UUID.randomUUID().toString();
        }

        return conversationId;
    }
}