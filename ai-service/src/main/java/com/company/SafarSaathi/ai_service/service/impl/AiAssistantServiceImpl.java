package com.company.SafarSaathi.ai_service.service.impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.orchestrator.AIOrchestratorService;
import com.company.SafarSaathi.ai_service.service.AiAssistantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiAssistantServiceImpl implements AiAssistantService {


    private final AIOrchestratorService orchestratorService;

    @Override
    public ChatResponse chat(ChatRequest request) {
        return orchestratorService.process(request);

    }

}