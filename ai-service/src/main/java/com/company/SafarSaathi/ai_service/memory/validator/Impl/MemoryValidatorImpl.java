package com.company.SafarSaathi.ai_service.memory.validator.Impl;


import com.company.SafarSaathi.ai_service.memory.dtos.ExtractedMemory;
import com.company.SafarSaathi.ai_service.memory.exceptions.InvalidMemoryException;
import com.company.SafarSaathi.ai_service.memory.validator.MemoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemoryValidatorImpl implements MemoryValidator {


    @Override
    public void validate(
            List<ExtractedMemory> memories
    ) {

        log.info(
                "Validating {} extracted memories.",
                memories.size()
        );

        for (ExtractedMemory memory : memories) {
            validateMemory(memory);
        }

        log.info("Memory validation completed successfully.");
    }

    private void validateMemory(
            ExtractedMemory memory
    ) {

        if (memory == null) {
            throw new InvalidMemoryException(
                    "Extracted memory cannot be null."
            );
        }

        if (memory.getCategory() == null) {
            throw new InvalidMemoryException(
                    "Memory category cannot be null."
            );
        }

        if (memory.getAttribute() == null
                || memory.getAttribute().isBlank()) {

            throw new InvalidMemoryException(
                    "Memory attribute cannot be empty."
            );
        }

        if (memory.getValue() == null
                || memory.getValue().isBlank()) {

            throw new InvalidMemoryException(
                    "Memory value cannot be empty."
            );
        }

        if (memory.getConfidence() == null) {
            throw new InvalidMemoryException(
                    "Memory confidence cannot be null."
            );
        }

        if (memory.getConfidence() < 0.0
                || memory.getConfidence() > 1.0) {

            throw new InvalidMemoryException(
                    "Memory confidence must be between 0.0 and 1.0."
            );
        }
    }
}
