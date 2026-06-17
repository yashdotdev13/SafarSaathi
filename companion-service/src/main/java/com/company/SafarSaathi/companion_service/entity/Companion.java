package com.company.SafarSaathi.companion_service.entity;

import com.company.SafarSaathi.companion_service.enums.CompanionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "companions",
        indexes = {
                @Index(
                        name = "idx_companion_user_id",
                        columnList = "userId"
                ),
                @Index(
                        name = "idx_companion_trip_id",
                        columnList = "tripId"
                ),
                @Index(
                        name = "idx_companion_status",
                        columnList = "status"
                )
        }
)
public class Companion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long tripId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanionStatus status;

    @Column(length = 500)
    private String message;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "companion_matches",
            joinColumns = @JoinColumn(name = "companion_id")
    )
    @Column(name = "matched_user_id")
    @Builder.Default
    private Set<Long> matchedUserIds = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}