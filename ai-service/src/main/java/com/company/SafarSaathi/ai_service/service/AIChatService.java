package com.company.SafarSaathi.ai_service.service;


import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;

public interface AIChatService {

    ChatResponse chat(
            ChatRequest request
    );

    ChatResponse chat(ChatRequest request, String prompt);

    String generateResponse(String prompt);

}
