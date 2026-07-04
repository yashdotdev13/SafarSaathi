package com.company.SafarSaathi.ai_service.tool.formatter;



import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToolResultFormatter {

    private final ObjectMapper objectMapper;

    public String format(Object data) {

        try {
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);
        } catch (Exception ex) {
            return String.valueOf(data);
        }
    }
}