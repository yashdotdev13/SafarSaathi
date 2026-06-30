package com.company.SafarSaathi.ai_service.service.prompt;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;

public interface PromptEnrichmentService {

    String enrich(ChatRequest request, ToolResponse toolResponse);
}
