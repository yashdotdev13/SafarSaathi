package com.company.SafarSaathi.ai_service.memory.parser;

import com.company.SafarSaathi.ai_service.memory.dtos.ExtractedMemory;

import java.util.List;

public interface  MemoryResponseParser {

    List<ExtractedMemory> parse(String response);
}
