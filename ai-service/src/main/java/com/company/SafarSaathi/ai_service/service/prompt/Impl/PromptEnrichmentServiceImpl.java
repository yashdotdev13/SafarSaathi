package com.company.SafarSaathi.ai_service.service.prompt.Impl;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import com.company.SafarSaathi.ai_service.tool.formatter.ToolResultFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromptEnrichmentServiceImpl
        implements PromptEnrichmentService {

    private final ToolResultFormatter toolResultFormatter;

    @Override
    public String enrich(
            ChatRequest request,
            @MonotonicNonNull String toolResponse
    ) {

        log.info(
                "Building enriched prompt for tool: {}",
                toolResponse.getToolType()
        );

        String formattedData = toolResultFormatter.format(
                toolResponse.getData()
        );

        return """
                You are SafarSaathi AI, an intelligent travel assistant.

                User Question:
                %s

                Tool Used:
                %s

                Tool Result:
                %s

                Instructions:
                - Answer ONLY using the tool result provided above.
                - Do NOT fabricate, assume, or infer information that is not present.
                - If the tool result is empty, politely inform the user that no relevant data was found.
                - Present the information in a natural, conversational manner.
                - Highlight important travel details such as destinations, dates, travel mode, and status whenever available.
                - Do not expose raw Java objects or internal implementation details.
                - Keep the response concise, accurate, and user-friendly.
                """
                .formatted(
                        request.getMessage(),
                        toolResponse.getToolType(),
                        formattedData
                );
    }
}