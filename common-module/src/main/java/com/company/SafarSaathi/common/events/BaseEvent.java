package com.company.SafarSaathi.common.events;


import com.company.SafarSaathi.common.enums.EventType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseEvent {

    private EventType eventType;

    private LocalDateTime timestamp;
}