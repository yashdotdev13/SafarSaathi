package com.company.SafarSaathi.ai_service.context.model;

import com.company.SafarSaathi.ai_service.context.enums.EntityType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolvedEntity {

    private EntityType type;

    private String value;

    private Object reference;

}