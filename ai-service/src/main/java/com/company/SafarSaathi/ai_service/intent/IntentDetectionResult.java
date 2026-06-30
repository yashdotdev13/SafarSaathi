package com.company.SafarSaathi.ai_service.intent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntentDetectionResult {

    private IntentType intentType;
    private double confidence;

    @Builder.Default
    private Map<String, Object> parameters = Map.of();
}
