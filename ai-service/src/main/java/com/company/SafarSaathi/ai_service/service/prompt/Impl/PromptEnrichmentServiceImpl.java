package com.company.SafarSaathi.ai_service.service.prompt.Impl;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PromptEnrichmentServiceImpl
        implements PromptEnrichmentService {

    @Override
    public String enrich(
            ChatRequest request,
            String mergedContext
    ) {

        log.info("Building enriched prompt.");

        return """
                You are SafarSaathi AI, an intelligent travel assistant.

                ========================================
                USER QUERY
                ========================================

                %s

                ========================================
                AVAILABLE CONTEXT
                ========================================

                %s

                ========================================
                INSTRUCTIONS
                ========================================

                - Answer ONLY using the provided context.
                - Never fabricate or assume information.
                - If some information is unavailable, clearly state that.
                - Present the response naturally and conversationally.
                - Highlight important travel details whenever available.
                - Do not expose raw JSON, Java objects, or internal implementation details.
                - Keep the response concise, accurate, and user-friendly.
                """
                .formatted(
                        request.getMessage(),
                        mergedContext
                );
    }
}