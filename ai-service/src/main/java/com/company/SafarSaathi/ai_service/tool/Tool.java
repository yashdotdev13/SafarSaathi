package com.company.SafarSaathi.ai_service.tool;

public interface Tool {

    ToolType getToolType();

    ToolResponse execute(ToolRequest request);
}
