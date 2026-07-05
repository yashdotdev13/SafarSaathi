package com.company.SafarSaathi.ai_service.planner.dto;


import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ExecutionPlan {

    private List<PlannedTool> tools;
    private ExecutionStrategy strategy;
}
