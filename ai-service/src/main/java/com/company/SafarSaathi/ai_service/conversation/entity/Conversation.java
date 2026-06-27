package com.company.SafarSaathi.ai_service.conversation.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "conversations",
        indexes = {
                @Index(name = "idx_conversation_user", columnList = "userId"),
                @Index(name = "idx_conversation_uuid", columnList = "conversationId", unique = true)
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID conversationId;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 150)
    private String title;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        if (conversationId == null) {
            conversationId = UUID.randomUUID();
        }

        active = true;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}