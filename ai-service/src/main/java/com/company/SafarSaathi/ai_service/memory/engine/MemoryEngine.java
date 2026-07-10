package com.company.SafarSaathi.ai_service.memory.engine;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;

public interface MemoryEngine {

    void extractAndPersistMemories(
            ConversationContext context
    );

}