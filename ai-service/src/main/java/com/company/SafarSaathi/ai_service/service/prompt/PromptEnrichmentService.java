package com.company.SafarSaathi.ai_service.service.prompt;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;

public interface PromptEnrichmentService {

    String enrich(
            ConversationContext context,
            String toolResponse
    );
}