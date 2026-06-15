package com.company.SafarSaathi.user_service.entities;

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
        name = "app_user",
        indexes = {
                @Index(
                        name = "idx_user_email",
                        columnList = "email"
                ),
                @Index(
                        name = "idx_user_country",
                        columnList = "country"
                ),
                @Index(
                        name = "idx_user_city",
                        columnList = "city"
                )
        }
)
public class User {

    @Id
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 20)
    private String gender;

    private Integer age;

    @Column(length = 500)
    private String bio;

    @Column(length = 100)
    private String country;

    @Column(length = 100)
    private String city;

    private boolean smoker;

    private boolean drinker;

    @Column(length = 100)
    private String lifestyle;

    @Column(length = 100)
    private String travelStyle;

    @Column(length = 500)
    private String profileImageUrl;

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