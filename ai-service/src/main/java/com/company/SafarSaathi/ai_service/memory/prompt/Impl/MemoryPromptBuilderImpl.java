package com.company.SafarSaathi.ai_service.memory.prompt.Impl;


import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.memory.prompt.MemoryPromptBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class MemoryPromptBuilderImpl
        implements MemoryPromptBuilder {

    @Override
    public String buildPrompt(
            ConversationContext context
    ) {

        log.info("Building memory extraction prompt.");

        String conversationHistory =
                context.getConversationHistory()
                        .stream()
                        .map(message ->
                                message.getRole() + ": " + message.getContent())
                        .collect(Collectors.joining("\n"));

        return """
                ====================================================
                SYSTEM ROLE
                ====================================================

                You are the Memory Extraction Engine for SafarSaathi.

                Your responsibility is ONLY to extract long-term user
                preferences and facts from the conversation.

                Never answer the user.

                Never generate conversational text.

                ====================================================
                OBJECTIVE
                ====================================================

                Identify information that should be remembered across
                future conversations.

                Extract ONLY stable user preferences, habits,
                or long-term facts.

                ====================================================
                MEMORY CATEGORIES
                ====================================================

                TRAVEL
                FOOD
                TRANSPORT
                ACCOMMODATION
                COMPANION
                DESTINATION
                LANGUAGE

                ====================================================
                CONVERSATION HISTORY
                ====================================================

                %s

                ====================================================
                CURRENT USER MESSAGE
                ====================================================

                %s

                ====================================================
                EXPECTED JSON FORMAT
                ====================================================

                [
                  {
                    "category":"TRAVEL",
                    "attribute":"style",
                    "value":"budget",
                    "confidence":0.95
                  }
                ]

                ====================================================
                EXAMPLES
                ====================================================

                Example 1

                User:
                I always travel on a budget.

                Output:

                [
                  {
                    "category":"TRAVEL",
                    "attribute":"style",
                    "value":"budget",
                    "confidence":0.95
                  }
                ]

                ----------------------------------------------------

                Example 2

                User:
                I prefer vegetarian food.

                Output:

                [
                  {
                    "category":"FOOD",
                    "attribute":"preference",
                    "value":"vegetarian",
                    "confidence":0.97
                  }
                ]

                ----------------------------------------------------

                Example 3

                User:
                Show my Goa trip.

                Output:

                []

                ----------------------------------------------------

                Example 4

                User:
                Recommend companions.

                Output:

                []

                ====================================================
                RULES
                ====================================================

                1. Return ONLY valid JSON.
                2. Never wrap JSON inside markdown.
                3. Never explain your reasoning.
                4. Extract ONLY long-term user preferences or facts.
                5. Ignore temporary requests and conversational context.
                6. Use ONLY the provided memory categories.
                7. Confidence must be between 0.0 and 1.0.
                8. Return an empty JSON array ([]) if no memory should be stored.
                """
                .formatted(
                        conversationHistory,
                        context.getChatRequest().getMessage()
                );
    }
}