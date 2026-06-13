package com.company.SafarSaathi.auth_service.exceptions;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private LocalDateTime timeStamp;
    private int status;

    private String error;

    private String message;

    private String path;
}
