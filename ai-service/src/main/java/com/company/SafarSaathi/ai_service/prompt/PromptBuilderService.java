package com.company.SafarSaathi.ai_service.prompt;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;

public interface  PromptBuilderService {

    String buildPrompt(PromptType promptType,
                       ChatRequest request);
}
