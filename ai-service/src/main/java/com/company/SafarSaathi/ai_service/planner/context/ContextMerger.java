package com.company.SafarSaathi.ai_service.planner.context;

import com.company.SafarSaathi.ai_service.tool.ToolResponse;

import java.util.List;

public interface ContextMerger {

    String merge(List<ToolResponse> responses);
}
