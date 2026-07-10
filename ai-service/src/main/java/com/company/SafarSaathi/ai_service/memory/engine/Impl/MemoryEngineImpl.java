package com.company.SafarSaathi.ai_service.memory.engine.Impl;

import com.company.SafarSaathi.ai_service.context.model.ConversationContext;
import com.company.SafarSaathi.ai_service.memory.dtos.ExtractedMemory;
import com.company.SafarSaathi.ai_service.memory.engine.MemoryEngine;
import com.company.SafarSaathi.ai_service.memory.entities.Memory;
import com.company.SafarSaathi.ai_service.memory.parser.MemoryResponseParser;
import com.company.SafarSaathi.ai_service.memory.prompt.MemoryPromptBuilder;
import com.company.SafarSaathi.ai_service.memory.service.MemoryService;
import com.company.SafarSaathi.ai_service.memory.validator.MemoryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryEngineImpl
        implements MemoryEngine {

    private final MemoryPromptBuilder memoryPromptBuilder;
    private final MemoryResponseParser memoryResponseParser;
    private final MemoryValidator memoryValidator;
    private final MemoryService memoryService;
    private final ChatClient chatClient;

    @Override
    public void extractAndPersistMemories(
            ConversationContext context
    ) {

        try {

            log.info("Starting memory extraction.");

            String prompt =
                    memoryPromptBuilder.buildPrompt(context);

            String response =
                    chatClient.prompt()
                            .user(prompt)
                            .call()
                            .content();

            List<ExtractedMemory> memories =
                    memoryResponseParser.parse(response);

            memoryValidator.validate(memories);

            saveMemories(
                    context.getConversation().getUserId(),
                    memories
            );

            log.info(
                    "Successfully extracted {} memories.",
                    memories.size()
            );

        } catch (Exception ex) {

            log.error(
                    "Memory extraction failed.",
                    ex
            );

        }

    }

    private void saveMemories(
            Long userId,
            List<ExtractedMemory> memories
    ) {

        for (ExtractedMemory extractedMemory : memories) {

            Memory memory =
                    Memory.builder()
                            .userId(userId)
                            .category(extractedMemory.getCategory())
                            .attribute(extractedMemory.getAttribute())
                            .value(extractedMemory.getValue())
                            .confidence(extractedMemory.getConfidence())
                            .source("USER_MESSAGE")
                            .build();

            memoryService.saveOrUpdateMemory(memory);
        }

    }

}
