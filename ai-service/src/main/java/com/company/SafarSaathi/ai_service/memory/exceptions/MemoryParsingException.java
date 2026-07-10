package com.company.SafarSaathi.ai_service.memory.exceptions;


public class MemoryParsingException
        extends RuntimeException {

    public MemoryParsingException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }

}