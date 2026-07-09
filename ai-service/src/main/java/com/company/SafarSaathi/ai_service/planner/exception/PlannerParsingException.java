package com.company.SafarSaathi.ai_service.planner.exception;

public class PlannerParsingException
        extends RuntimeException {

    public PlannerParsingException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}