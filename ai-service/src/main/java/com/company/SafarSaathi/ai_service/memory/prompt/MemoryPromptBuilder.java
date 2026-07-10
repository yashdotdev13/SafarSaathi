package com.company.SafarSaathi.ai_service.memory.prompt;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;

public interface MemoryPromptBuilder {

    String buildPrompt(ConversationContext context);
}
