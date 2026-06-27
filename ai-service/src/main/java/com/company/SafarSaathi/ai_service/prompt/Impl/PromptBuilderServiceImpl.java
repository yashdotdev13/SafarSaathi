package com.company.SafarSaathi.ai_service.prompt.Impl;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.prompt.SystemPrompts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PromptBuilderServiceImpl implements PromptBuilderService {


    @Override
    public String buildPrompt(PromptType promptType, ChatRequest request) {


        log.debug(
                "Building prompt for type={}",
                promptType
        );

        return switch (promptType) {

            case CHAT ->
                    buildChatPrompt(request);

            case TRIP_PLANNER ->
                    throw new UnsupportedOperationException(
                            "Trip planner prompt is not implemented yet."
                    );

            case COMPANION_MATCH ->
                    throw new UnsupportedOperationException(
                            "Companion match prompt is not implemented yet."
                    );

            case DESTINATION_GUIDE ->
                    throw new UnsupportedOperationException(
                            "Destination guide prompt is not implemented yet."
                    );

            case ITINERARY ->
                    throw new UnsupportedOperationException(
                            "Itinerary prompt is not implemented yet."
                    );

            case BUDGET ->
                    throw new UnsupportedOperationException(
                            "Budget prompt is not implemented yet."
                    );

            case PACKING ->
                    throw new UnsupportedOperationException(
                            "Packing prompt is not implemented yet."
                    );
        };
    }

    private String buildChatPrompt(
            ChatRequest request
    ) {

        return """
                %s

                User Query:
                %s
                """
                .formatted(
                        SystemPrompts.CHAT,
                        request.getMessage()
                );
    }
}
