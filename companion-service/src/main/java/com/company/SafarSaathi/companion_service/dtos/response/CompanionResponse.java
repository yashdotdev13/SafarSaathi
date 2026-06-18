package com.company.SafarSaathi.companion_service.dtos.response;

import com.company.SafarSaathi.companion_service.enums.CompanionStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanionResponse {

    private Long id;
    private Long userId;
    private Long tripId;
    private CompanionStatus status;
    private String message;
    private Set<Long> matchedUserIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}