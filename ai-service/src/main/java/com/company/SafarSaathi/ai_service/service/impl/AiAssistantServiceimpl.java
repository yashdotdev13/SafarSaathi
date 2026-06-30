package com.company.SafarSaathi.ai_service.service.impl;


import com.company.SafarSaathi.ai_service.auth.UserContextHolder;
import com.company.SafarSaathi.ai_service.conversation.entity.Conversation;
import com.company.SafarSaathi.ai_service.conversation.entity.ConversationMessage;
import com.company.SafarSaathi.ai_service.conversation.enums.MessageRole;
import com.company.SafarSaathi.ai_service.conversation.service.ConversationService;
import com.company.SafarSaathi.ai_service.dtos.ChatRequest;
import com.company.SafarSaathi.ai_service.dtos.ChatResponse;
import com.company.SafarSaathi.ai_service.prompt.PromptBuilderService;
import com.company.SafarSaathi.ai_service.prompt.PromptType;
import com.company.SafarSaathi.ai_service.service.AiAssistantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AiAssistantServiceimpl implements AiAssistantService {


}