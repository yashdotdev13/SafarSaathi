package com.company.SafarSaathi.ai_service.service.prompt;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;

public interface PromptEnrichmentService {

    String enrich(ChatRequest request, String toolResponse);
}
