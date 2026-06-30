package com.company.SafarSaathi.ai_service.orchestrator.Impl;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionService;
import com.company.SafarSaathi.ai_service.orchestrator.AIOrchestratorService;
import com.company.SafarSaathi.ai_service.tool.ToolExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIOrchestratorServiceImpl implements AIOrchestratorService {

    private final IntentDetectionService intentDetectionService;
    private final ToolExecutor toolExecutor;
    private final ChatClient chatClint;


    @Override
    public ChatResponse process(ChatRequest request) {

        log.info("Processing request through AI orchestrator");
        return null;
    }
}
