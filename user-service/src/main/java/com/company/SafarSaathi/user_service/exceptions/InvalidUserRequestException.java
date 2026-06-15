package com.company.SafarSaathi.user_service.exceptions;

public class InvalidUserRequestException extends RuntimeException {
    public InvalidUserRequestException(String message) {
        super(message);
    }
}
