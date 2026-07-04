package com.company.SafarSaathi.ai_service.tool;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolMetadata {

    private ToolType toolType;

    private String description;

    private boolean enabled;
}
