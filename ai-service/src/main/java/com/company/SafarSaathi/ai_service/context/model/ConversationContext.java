package com.company.SafarSaathi.ai_service.context.model;


import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationContext {

    private ChatRequest request;
    private Conversation conversation;

    private List<ConversationMessage> conversationMessage;
}
