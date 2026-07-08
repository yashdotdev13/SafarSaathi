package com.company.SafarSaathi.ai_service.service.prompt.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ResolvedEntity;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class PromptEnrichmentServiceImpl
        implements PromptEnrichmentService {

    @Override
    public String enrich(
            ConversationContext context,
            String mergedContext
    ) {

        log.info("Building enriched prompt.");

        String conversationHistory =
                context.getConversationHistory()
                        .stream()
                        .map(message ->
                                message.getRole() + ": " + message.getContent())
                        .collect(Collectors.joining("\n"));

        String resolvedEntities =
                context.getResolvedEntities()
                        .stream()
                        .map(ResolvedEntity::getType)
                        .map(Enum::name)
                        .collect(Collectors.joining(", "));

        return """
                You are SafarSaathi AI, an intelligent travel assistant.

                ========================================
                CURRENT USER QUERY
                ========================================

                %s

                ========================================
                CONVERSATION HISTORY
                ========================================

                %s

                ========================================
                CONVERSATION STATE
                ========================================

                Current Intent      : %s
                Current Trip Id     : %s
                Current Destination : %s
                Current Companion   : %s

                ========================================
                RESOLVED ENTITIES
                ========================================

                %s

                ========================================
                TOOL CONTEXT
                ========================================

                %s

                ========================================
                INSTRUCTIONS
                ========================================

                - Use the conversation history to understand follow-up questions.
                - Prefer resolved entities and conversation state whenever available.
                - Answer ONLY using the provided tool context.
                - Never fabricate or assume information.
                - If information is unavailable, clearly mention it.
                - Keep responses conversational and natural.
                - Do not expose internal implementation details.
                """
                .formatted(
                        context.getChatRequest().getMessage(),
                        conversationHistory,
                        context.getConversationState().getCurrentIntent(),
                        context.getConversationState().getCurrentTripId(),
                        context.getConversationState().getCurrentDestination(),
                        context.getConversationState().getCurrentCompanionId(),
                        resolvedEntities.isBlank() ? "None" : resolvedEntities,
                        mergedContext
                );
    }
}