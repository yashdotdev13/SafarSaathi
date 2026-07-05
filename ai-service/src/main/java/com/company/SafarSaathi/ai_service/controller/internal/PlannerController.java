package com.company.SafarSaathi.ai_service.controller.internal;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.planner.AIPlanner;
import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlannerController {

    private final AIPlanner aiPlanner;

    @PostMapping
    public ExecutionPlan plan(
            @RequestBody ChatRequest request
    ) {
        return aiPlanner.createPlan(request);
    }
}