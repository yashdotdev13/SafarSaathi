package com.company.SafarSaathi.ai_service.context.resolver;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;

public interface ConversationContextResolver {


    ConversationContext resolve(ChatRequest request);
}
