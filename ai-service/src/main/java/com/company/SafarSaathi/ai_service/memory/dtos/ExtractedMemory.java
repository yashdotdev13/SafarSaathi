package com.company.SafarSaathi.ai_service.memory.dtos;


import com.company.SafarSaathi.ai_service.memory.enums.MemoryCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedMemory {


    private MemoryCategory category;

    private String attribute;

    private String value;

    private Double confidence;
}
