package com.company.SafarSaathi.companion_service.entity;

import com.company.SafarSaathi.companion_service.enums.RequestStatus;
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
        name = "companion_requests",
        indexes = {

                @Index(
                        name = "idx_request_sender",
                        columnList = "senderId"
                ),

                @Index(
                        name = "idx_request_receiver",
                        columnList = "receiverId"
                ),

                @Index(
                        name = "idx_request_trip",
                        columnList = "tripId"
                ),

                @Index(
                        name = "idx_request_status",
                        columnList = "status"
                )
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_sender_receiver_trip",
                        columnNames = {
                                "senderId",
                                "receiverId",
                                "tripId"
                        }
                )
        }
)
public class CompanionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    @Column(nullable = false)
    private Long tripId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(length = 500)
    private String message;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @PrePersist
    public void onCreate() {
        this.timeStamp = LocalDateTime.now();
    }
}