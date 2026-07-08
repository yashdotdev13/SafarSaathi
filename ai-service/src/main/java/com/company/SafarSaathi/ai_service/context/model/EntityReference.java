package com.company.SafarSaathi.ai_service.context.model;


import com.company.SafarSaathi.ai_service.context.enums.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityReference {

    private EntityType entityType;

    private String identifier;
}
