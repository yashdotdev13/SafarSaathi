package com.company.SafarSaathi.ai_service.planner.prompt;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;

public interface PlannerPromptBuilder {

    String buildPrompt(ConversationContext context);
}
