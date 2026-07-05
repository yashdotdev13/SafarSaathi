package com.company.SafarSaathi.ai_service.planner.dto;


import com.company.SafarSaathi.ai_service.planner.enums.ExecutionStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExecutionPlan {

    private List<PlannedTool> tools;
    private ExecutionStrategy strategy;
}
