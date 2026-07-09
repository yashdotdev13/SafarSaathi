package com.company.SafarSaathi.ai_service.service.prompt.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ResolvedEntity;
import com.company.SafarSaathi.ai_service.memory.entities.Memory;
import com.company.SafarSaathi.ai_service.memory.service.MemoryService;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromptEnrichmentServiceImpl
        implements PromptEnrichmentService {

    private final MemoryService memoryService;

    @Override
    public String enrich(
            ConversationContext context,
            String mergedContext
    ) {

        log.info("Building enriched prompt.");

        Long userId = context.getConversation().getUserId();

        List<Memory> memories =
                memoryService.getUserMemories(userId);

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
                USER MEMORIES
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
                - Use the stored user memories whenever they are relevant.
                - Respect the user's long-term preferences while answering.
                - Prefer resolved entities and conversation state whenever available.
                - Answer ONLY using the provided tool context.
                - Never fabricate or assume information.
                - If information is unavailable, clearly mention it.
                - Keep responses conversational and natural.
                - Do not expose internal implementation details.
                """
                .formatted(
                        context.getChatRequest().getMessage(),
                        buildConversationHistory(context),
                        context.getConversationState().getCurrentIntent(),
                        context.getConversationState().getCurrentTripId(),
                        context.getConversationState().getCurrentDestination(),
                        context.getConversationState().getCurrentCompanionId(),
                        buildResolvedEntities(context),
                        buildMemorySection(memories),
                        mergedContext
                );
    }

    private String buildConversationHistory(
            ConversationContext context
    ) {

        if (context.getConversationHistory().isEmpty()) {
            return "No conversation history available.";
        }

        return context.getConversationHistory()
                .stream()
                .map(message ->
                        message.getRole() + ": " + message.getContent())
                .collect(Collectors.joining("\n"));
    }

    private String buildResolvedEntities(
            ConversationContext context
    ) {

        if (context.getResolvedEntities().isEmpty()) {
            return "None";
        }

        return context.getResolvedEntities()
                .stream()
                .map(ResolvedEntity::getType)
                .map(Enum::name)
                .collect(Collectors.joining(", "));
    }

    private String buildMemorySection(
            List<Memory> memories
    ) {

        if (memories == null || memories.isEmpty()) {
            return "No long-term user memories available.";
        }

        return memories.stream()
                .map(memory -> """
                        [%s]
                        %s : %s
                        """
                        .formatted(
                                memory.getCategory(),
                                memory.getAttribute(),
                                memory.getValue()
                        ))
                .collect(Collectors.joining("\n"));
    }
}