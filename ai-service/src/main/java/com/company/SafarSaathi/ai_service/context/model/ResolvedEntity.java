package com.company.SafarSaathi.ai_service.context.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResolvedEntity {

    private String type;
    private String value;

    private Object reference;
}
