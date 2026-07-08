package com.company.SafarSaathi.ai_service.context.model;

import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationContext {

    private ChatRequest chatRequest;

    private Conversation conversation;

    private List<ConversationMessage> conversationHistory;

    private ConversationState conversationState;

}