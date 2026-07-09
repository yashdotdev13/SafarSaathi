package com.company.SafarSaathi.ai_service.memory.service.Impl;


import com.company.SafarSaathi.ai_service.memory.entities.Memory;
import com.company.SafarSaathi.ai_service.memory.enums.MemoryCategory;
import com.company.SafarSaathi.ai_service.memory.repositories.MemoryRepository;
import com.company.SafarSaathi.ai_service.memory.service.MemoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemoryServiceImpl implements MemoryService {

    private final MemoryRepository memoryRepository;

    @Override
    public List<Memory> getUserMemories(Long userId) {

        log.info("Fetching memories for userId={}", userId);

        return memoryRepository.
                findByUserIdOrderByCategoryAscAttributeAsc(
                userId
        );
    }

    @Override
    public List<Memory> getUserMemories(
            Long userId,
            MemoryCategory category
    ) {

        log.info(
                "Fetching {} memories for userId={}",
                category,
                userId
        );

        return memoryRepository.findByUserIdAndCategory(
                userId,
                category
        );
    }

    @Override
    public Memory saveOrUpdateMemory(
            Memory memory
    ) {

        log.info(
                "Saving memory. userId={}, category={}, attribute={}",
                memory.getUserId(),
                memory.getCategory(),
                memory.getAttribute()
        );

        Memory existingMemory =
                memoryRepository
                        .findByUserIdAndCategoryAndAttribute(
                                memory.getUserId(),
                                memory.getCategory(),
                                memory.getAttribute()
                        )
                        .orElse(null);

        if (existingMemory == null) {

            log.info("Creating new memory.");

            return memoryRepository.save(memory);
        }

        log.info("Updating existing memory.");

        existingMemory.setValue(memory.getValue());
        existingMemory.setConfidence(memory.getConfidence());
        existingMemory.setSource(memory.getSource());

        return memoryRepository.save(existingMemory);
    }
}