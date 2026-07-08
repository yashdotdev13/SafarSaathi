package com.company.SafarSaathi.ai_service.context.service;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ConversationState;

public interface ConversationStateService {

    ConversationState buildState(ConversationContext context);
}
