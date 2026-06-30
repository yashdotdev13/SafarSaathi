package com.company.SafarSaathi.ai_service.controller.internal;

import com.company.SafarSaathi.ai_service.intent.IntentDetectionResult;
import com.company.SafarSaathi.ai_service.intent.IntentDetectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntentTestController {

    private final IntentDetectionService intentDetectionService;

    @GetMapping("/internal/intent")
    public IntentDetectionResult detectIntent(
            @RequestParam String query
    ) {

        return intentDetectionService.detectIntent(query);
    }
}