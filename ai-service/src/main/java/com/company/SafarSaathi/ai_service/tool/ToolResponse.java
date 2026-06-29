package com.company.SafarSaathi.ai_service.tool;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolResponse {

    private ToolType toolType;
    private Boolean success;
    private String message;
    private Object data;
}
