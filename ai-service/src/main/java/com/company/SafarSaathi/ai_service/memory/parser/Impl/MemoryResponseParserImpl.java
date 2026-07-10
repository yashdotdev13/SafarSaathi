package com.company.SafarSaathi.ai_service.memory.parser.Impl;

import com.company.SafarSaathi.ai_service.memory.dtos.ExtractedMemory;
import com.company.SafarSaathi.ai_service.memory.exceptions.MemoryParsingException;
import com.company.SafarSaathi.ai_service.memory.parser.MemoryResponseParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryResponseParserImpl implements MemoryResponseParser {

    private final ObjectMapper objectMapper;


    @Override
    public List<ExtractedMemory> parse(
            String response
    ) {

        log.info("Parsing memory extraction response.");

        try {

            List<ExtractedMemory> memories =
                    objectMapper.readValue(
                            response,
                            new TypeReference<List<ExtractedMemory>>() {}
                    );

            log.info(
                    "Successfully parsed {} extracted memories.",
                    memories.size()
            );

            return memories;

        } catch (JsonProcessingException ex) {

            log.error(
                    "Failed to parse memory extraction response.",
                    ex
            );

            throw new MemoryParsingException(
                    "Unable to parse memory extraction response.",
                    ex
            );
        }
    }
}