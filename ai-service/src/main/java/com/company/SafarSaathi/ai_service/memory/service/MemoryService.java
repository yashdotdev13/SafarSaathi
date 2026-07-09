package com.company.SafarSaathi.ai_service.memory.service;

import com.company.SafarSaathi.ai_service.memory.entities.Memory;
import com.company.SafarSaathi.ai_service.memory.enums.MemoryCategory;

import java.util.List;

public interface MemoryService {

    List<Memory> getUserMemories(Long userId);

    List<Memory> getUserMemories(Long userId, MemoryCategory category);

    Memory saveOrUpdateMemory(Memory memory);
}
