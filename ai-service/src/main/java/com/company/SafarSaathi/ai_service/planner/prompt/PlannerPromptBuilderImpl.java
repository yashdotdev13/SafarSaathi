package com.company.SafarSaathi.ai_service.planner.prompt;


import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ResolvedEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class PlannerPromptBuilderImpl
        implements PlannerPromptBuilder {

    @Override
    public String buildPrompt(
            ConversationContext context
    ) {

        log.info("Building planner prompt.");

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
                ====================================================
                SYSTEM ROLE
                ====================================================

                You are the AI Planning Engine for SafarSaathi.

                Your responsibility is ONLY to decide which backend tools
                should be executed.

                Never answer the user.

                Never generate conversational text.

                ====================================================
                AVAILABLE TOOLS
                ====================================================

                USER
                Description:
                Retrieve the authenticated user's profile.

                ----------------------------------------------------

                TRIP
                Description:
                Retrieve trips belonging to the authenticated user.

                ----------------------------------------------------

                COMPANION
                Description:
                Retrieve companion recommendations and companion profile.

                ====================================================
                CONVERSATION HISTORY
                ====================================================

                %s

                ====================================================
                CURRENT USER QUERY
                ====================================================

                %s

                ====================================================
                CONVERSATION STATE
                ====================================================

                Current Intent      : %s
                Current Trip Id     : %s
                Current Destination : %s
                Current Companion   : %s

                ====================================================
                RESOLVED ENTITIES
                ====================================================

                %s

                ====================================================
                EXPECTED JSON FORMAT
                ====================================================

                {
                  "strategy":"SEQUENTIAL",
                  "tools":[
                    {
                      "toolType":"TRIP",
                      "order":1,
                      "required":true,
                      "reason":"Need trip information."
                    }
                  ]
                }

                ====================================================
                PLANNING RULES
                ====================================================

                1. Return ONLY valid JSON.
                2. Never use markdown.
                3. Never explain your reasoning.
                4. Use ONLY these tool names:
                   USER
                   TRIP
                   COMPANION
                5. Never invent tools.
                6. Use SEQUENTIAL execution.
                7. If no tool is needed, return:

                {
                  "strategy":"SEQUENTIAL",
                  "tools":[]
                }

                """
                .formatted(
                        conversationHistory,
                        context.getChatRequest().getMessage(),
                        context.getConversationState().getCurrentIntent(),
                        context.getConversationState().getCurrentTripId(),
                        context.getConversationState().getCurrentDestination(),
                        context.getConversationState().getCurrentCompanionId(),
                        resolvedEntities.isBlank()
                                ? "None"
                                : resolvedEntities
                );
    }
}