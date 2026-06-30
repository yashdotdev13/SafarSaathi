package com.company.SafarSaathi.ai_service.orchestrator;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;

public interface AIOrchestratorService {

    ChatResponse process(ChatRequest request);
}
