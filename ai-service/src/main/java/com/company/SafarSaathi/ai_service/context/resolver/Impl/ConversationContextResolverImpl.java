package com.company.SafarSaathi.ai_service.context.resolver.Impl;


import com.company.SafarSaathi.ai_service.auth.UserContextHolder;
import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.resolver.ConversationContextResolver;
import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationContextResolverImpl
        implements ConversationContextResolver {

    private final ConversationService conversationService;

    @Override
    public ConversationContext resolve(ChatRequest request) {

        log.info("Resolving conversation context.");

        Long currentUserId = UserContextHolder.getCurrentUserId();

        Conversation conversation =
                conversationService.getOrCreateConversation(
                        currentUserId,
                        request.getConversationId()
                );

        log.debug(
                "Conversation resolved. conversationId={}",
                conversation.getConversationId()
        );

        List<ConversationMessage> history =
                conversationService.getConversationHistory(
                        conversation
                );

        log.debug(
                "Fetched {} conversation messages.",
                history.size()
        );

        ConversationContext context =
                ConversationContext.builder()
                        .chatRequest(request)
                        .conversation(conversation)
                        .conversationHistory(history)
                        .build();

        log.info(
                "Conversation context successfully created."
        );

        return context;
    }
}