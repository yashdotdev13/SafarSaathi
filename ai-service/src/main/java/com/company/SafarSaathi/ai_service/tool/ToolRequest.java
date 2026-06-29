package com.company.SafarSaathi.ai_service.tool;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolRequest {

    private ToolType toolType;
    private Long userId;

    private String conversationId;

    private String query;

    @Builder.Default
    private Map<String, Object> parameters = Map.of();
}
