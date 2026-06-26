package com.company.SafarSaathi.ai_service.service;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;



public interface AiAssistantService {

    ChatResponse chat(ChatRequest request);
}
