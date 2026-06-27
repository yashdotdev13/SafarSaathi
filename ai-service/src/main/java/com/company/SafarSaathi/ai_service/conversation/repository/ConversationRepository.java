package com.company.SafarSaathi.ai_service.conversation.repository;

import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {


    Optional<Conversation> findByConversationId(
            UUID conversationId
    );

    boolean existsByConversationId(
            UUID conversationId
    );
}
