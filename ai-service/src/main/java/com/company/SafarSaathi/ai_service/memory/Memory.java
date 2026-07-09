package com.company.SafarSaathi.ai_service.memory;


import com.company.SafarSaathi.ai_service.memory.enums.MemoryCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "memories",
        indexes = {
                @Index(name = "idx_memory_user", columnList = "userId"),
                @Index(name = "idx_memory_category", columnList = "category"),
                @Index(name = "idx_memory_user_category", columnList = "userId,category")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;


    /**
     * High-level memory classification.
     *
     * Example:
     * TRAVEL
     * FOOD
     * TRANSPORT
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private MemoryCategory category;

    /**
     * Specific property inside the category.
     *
     * Examples:
     * style
     * seat
     * preference
     * activity
     */
    @Column(nullable = false, length = 100)
    private String attribute;

    /**
     * Stored value.
     *
     * Examples:
     * budget
     * vegetarian
     * window
     */
    @Column(nullable = false, length = 255)
    private String value;

    /**
     * Confidence score assigned by the AI.
     *
     * Range:
     * 0.0 - 1.0
     */
    @Column(nullable = false)
    @Builder.Default
    private Double confidence = 1.0;

    /**
     * Source of the memory.
     *
     * Examples:
     * USER_MESSAGE
     * PROFILE
     * SYSTEM
     * MANUAL
     */
    @Column(nullable = false, length = 100)
    private String source;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
