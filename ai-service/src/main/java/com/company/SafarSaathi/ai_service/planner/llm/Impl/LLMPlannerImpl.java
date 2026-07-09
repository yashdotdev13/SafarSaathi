package com.company.SafarSaathi.ai_service.planner.llm.Impl;


import com.company.SafarSaathi.ai_service.planner.llm.LLMPlanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LLMPlannerImpl implements LLMPlanner {

    private final ChatClient chatClient;

    @Override
    public String generatePlan(String plannerPrompt) {


        log.info("Generating execution plan using Gemini.");

        String response = chatClient
                .prompt()
                .user(plannerPrompt)
                .call()
                .content();

        log.info("Execution plan generated successfully.");

        return response;
    }
}
