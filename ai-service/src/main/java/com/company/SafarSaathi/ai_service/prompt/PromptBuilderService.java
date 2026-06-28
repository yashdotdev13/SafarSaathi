package com.company.SafarSaathi.ai_service.prompt;

import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;

import java.util.List;

public interface  PromptBuilderService {

    String buildPrompt(
            PromptType promptType,
            ChatRequest request,
            List<ConversationMessage> history
    );
}
