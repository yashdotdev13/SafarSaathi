package com.company.SafarSaathi.ai_service.context.resolver.Impl;

import com.company.SafarSaathi.ai_service.context.enums.EntityType;
import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.context.model.ResolvedEntity;
import com.company.SafarSaathi.ai_service.context.resolver.EntityResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntityResolverImpl implements EntityResolver {

    @Override
    public void resolve(ConversationContext context) {

        log.info("Resolving entities from conversation.");

        String query = context.getChatRequest()
                .getMessage()
                .toLowerCase();

        resolveTrip(query, context);
        resolveDestination(query, context);
        resolveCompanion(query, context);
        resolveUser(query, context);

        log.info(
                "Entity resolution completed. {} entity(ies) resolved.",
                context.getResolvedEntities().size()
        );
    }

    private void resolveTrip(
            String query,
            ConversationContext context
    ) {

        if (query.contains("trip")
                || query.contains("journey")
                || query.contains("vacation")
                || query.contains("itinerary")) {

            context.getResolvedEntities().add(
                    ResolvedEntity.builder()
                            .type(EntityType.TRIP)
                            .value("trip")
                            .reference(null)
                            .build()
            );
        }
    }

    private void resolveDestination(
            String query,
            ConversationContext context
    ) {

        if (query.contains("destination")) {

            context.getResolvedEntities().add(
                    ResolvedEntity.builder()
                            .type(EntityType.DESTINATION)
                            .value("destination")
                            .reference(null)
                            .build()
            );
        }
    }

    private void resolveCompanion(
            String query,
            ConversationContext context
    ) {

        if (query.contains("companion")
                || query.contains("buddy")
                || query.contains("travel partner")) {

            context.getResolvedEntities().add(
                    ResolvedEntity.builder()
                            .type(EntityType.COMPANION)
                            .value("companion")
                            .reference(null)
                            .build()
            );
        }
    }

    private void resolveUser(
            String query,
            ConversationContext context
    ) {

        if (query.contains("profile")
                || query.contains("myself")
                || query.contains("account")
                || query.contains("my information")) {

            context.getResolvedEntities().add(
                    ResolvedEntity.builder()
                            .type(EntityType.USER)
                            .value("current-user")
                            .reference(null)
                            .build()
            );
        }
    }
}