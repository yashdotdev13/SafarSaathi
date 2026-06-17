package com.company.SafarSaathi.companion_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "companion_preferences",
        indexes = {
                @Index(
                        name = "idx_preference_user_id",
                        columnList = "userId"
                )
        }
)
public class CompanionPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true
    )
    private Long userId;

    private Integer preferredAgeMin;

    private Integer preferredAgeMax;

    @Column(length = 20)
    private String preferredGender;

    private Boolean smokerOk;

    private Boolean drinkerOk;

    @Column(length = 100)
    private String preferredTripMode;

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