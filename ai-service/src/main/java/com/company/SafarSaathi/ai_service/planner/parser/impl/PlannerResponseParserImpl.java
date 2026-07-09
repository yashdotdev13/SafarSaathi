package com.company.SafarSaathi.ai_service.planner.parser.impl;

import com.company.SafarSaathi.ai_service.planner.dto.ExecutionPlan;
import com.company.SafarSaathi.ai_service.planner.exception.PlannerParsingException;
import com.company.SafarSaathi.ai_service.planner.parser.PlannerResponseParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlannerResponseParserImpl implements PlannerResponseParser {

    private final ObjectMapper objectMapper;

    @Override
    public ExecutionPlan parse(String plannerResponse) {


        log.info("Parsing Planner response");

        try{

            return objectMapper.readValue(
                    plannerResponse,
                    ExecutionPlan.class
            );
        }catch(JsonProcessingException ex){
            log.error("Failed to parse planner response",ex);
            throw new PlannerParsingException("Failed to parse planner response",ex);
        }
    }
}
