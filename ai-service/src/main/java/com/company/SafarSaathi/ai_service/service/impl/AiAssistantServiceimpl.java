package com.company.SafarSaathi.ai_service.service.impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
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

    @Override
    public ChatResponse chat(ChatRequest request) {

        log.info("Received AI chat request.");

        String conversationId = resolveConversationId(
                request.getConversationId()
        );

        String aiResponse = chatClient.prompt()
                .user(request.getMessage())
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