package com.company.SafarSaathi.ai_service.service;

import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiChatService {


    public ChatResponse chat(ChatRequest request){

        log.info("Received AI chat request: {}",request.getMessage());

        return ChatResponse.builder()
                .response("AI service is working")
                .build();
    }
}
