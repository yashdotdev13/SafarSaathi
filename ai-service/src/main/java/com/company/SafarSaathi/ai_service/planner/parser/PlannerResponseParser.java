package com.company.SafarSaathi.ai_service.planner.parser;

import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;

public interface PlannerResponseParser {

    ExecutionPlan parse(String plannerResponse);
}
