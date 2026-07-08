package com.company.SafarSaathi.ai_service.context.model;


import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContextSnapshot {

    private ConversationMessage context;

    private ConversationState state;
}
