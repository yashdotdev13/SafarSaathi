package com.company.SafarSaathi.ai_service.prompt.Impl;

import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.prompt.SystemPrompts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class PromptBuilderServiceImpl implements PromptBuilderService {

    @Override
    public String buildPrompt(
            PromptType promptType,
            ChatRequest request,
            List<ConversationMessage> history
    ) {

        log.debug(
                "Building {} prompt with {} previous messages.",
                promptType,
                history.size()
        );

        return switch (promptType) {

            case CHAT ->
                    buildChatPrompt(
                            request,
                            history
                    );

            case TRIP_PLANNER ->
                    throw new UnsupportedOperationException();

            case COMPANION_MATCH ->
                    throw new UnsupportedOperationException();

            case DESTINATION_GUIDE ->
                    throw new UnsupportedOperationException();

            case ITINERARY ->
                    throw new UnsupportedOperationException();

            case BUDGET ->
                    throw new UnsupportedOperationException();

            case PACKING ->
                    throw new UnsupportedOperationException();
        };
    }


    private String buildChatPrompt(
            ChatRequest request,
            List<ConversationMessage> history
    ) {

        StringBuilder prompt = new StringBuilder();

        prompt.append(SystemPrompts.CHAT)
                .append("\n\n");

        if (!history.isEmpty()) {

            prompt.append("Conversation History:\n");

            history.forEach(message ->

                    prompt.append(message.getRole())
                            .append(": ")
                            .append(message.getContent())
                            .append("\n")
            );

            prompt.append("\n");
        }

        prompt.append("Current User Query:\n")
                .append(request.getMessage());

        return prompt.toString();
    }
}
