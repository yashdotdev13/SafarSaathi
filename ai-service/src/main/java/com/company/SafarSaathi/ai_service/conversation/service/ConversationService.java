package com.company.SafarSaathi.ai_service.conversation.service;

import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.enums.MessageRole;

import java.util.List;

public interface ConversationService {


    Conversation getOrCreateConversation(Long userId,
                                         String conversationId
    );

    void saveMessage(Conversation conversation, MessageRole role, String content);
    List<String> getConversationHistory(Conversation conversation);
}
