package com.company.SafarSaathi.ai_service.planner.dto;


import com.company.SafarSaathi.ai_service.tool.ToolType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlannedTool {

    private ToolType toolType;
    private Integer order;
}
