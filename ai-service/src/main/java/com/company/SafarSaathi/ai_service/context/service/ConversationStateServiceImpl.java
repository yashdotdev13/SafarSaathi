package com.company.SafarSaathi.ai_service.context.service;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ConversationState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConversationStateServiceImpl implements ConversationStateService{




    @Override
    public ConversationState buildState(ConversationContext context) {


        log.info("Building conversation state:");

        ConversationState state = ConversationState.builder()
                .currentIntent(null)
                .currentTripId(null)
                .currentDestination(null)
                .currentCompanionId(null)
                .build();

        log.info("Conversation state created");

        return state;
    }
}
