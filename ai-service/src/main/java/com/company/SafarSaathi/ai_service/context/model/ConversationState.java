package com.company.SafarSaathi.ai_service.context.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationState {

    private String currentIntent;

    private Long currentTripId;

    private String currentDestination;

    private Long currentCompanionId;
}
