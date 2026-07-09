package com.company.SafarSaathi.ai_service.memory.repositories;


import com.company.SafarSaathi.ai_service.memory.entities.Memory;
import com.company.SafarSaathi.ai_service.memory.enums.MemoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findByUserId(Long userId);

    List<Memory> findByUserIdOrderByCategoryAscAttributeAsc(Long userId);

    List<Memory> findByUserIdAndCategory(
            Long userId,
            MemoryCategory category
    );

    Optional<Memory> findByUserIdAndCategoryAndAttribute(
            Long userId,
            MemoryCategory category,
            String attribute
    );

    boolean existsByUserIdAndCategoryAndAttribute(
            Long userId,
            MemoryCategory category,
            String attribute
    );

}