package com.company.SafarSaathi.ai_service.tool;

public class ToolException extends RuntimeException {

    public ToolException(String message) {
        super(message);
    }

    public ToolException(String message, Throwable cause) {
        super(message, cause);
    }
}