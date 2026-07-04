package com.company.SafarSaathi.ai_service.tool;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToolContext {

    private Long userId;

    private String conversationId;

    private String query;
}
