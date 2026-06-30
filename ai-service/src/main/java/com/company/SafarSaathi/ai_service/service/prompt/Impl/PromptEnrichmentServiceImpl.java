package com.company.SafarSaathi.ai_service.service.prompt.Impl;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.service.prompt.PromptEnrichmentService;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PromptEnrichmentServiceImpl
        implements PromptEnrichmentService {

    @Override
    public String enrich(
            ChatRequest request,
            ToolResponse toolResponse
    ) {

        log.info("Building enriched prompt.");

        return """
                You are SafarSaathi AI.

                User Question:
                %s

                Available Data:
                %s

                Instructions:
                - Answer only using the provided data.
                - Do not fabricate information.
                - Be concise and helpful.
                - Format the answer naturally.
                """
                .formatted(
                        request.getMessage(),
                        toolResponse.getData()
                );
    }
}