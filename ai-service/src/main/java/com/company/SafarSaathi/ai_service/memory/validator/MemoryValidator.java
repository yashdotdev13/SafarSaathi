package com.company.SafarSaathi.ai_service.memory.validator;

import com.company.SafarSaathi.ai_service.memory.dtos.ExtractedMemory;

import java.util.List;

public interface MemoryValidator {

    void validate(List<ExtractedMemory> memories);
}
