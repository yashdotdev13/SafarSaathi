package com.company.SafarSaathi.auth_service.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {

    private String accessToken;

    private String tokenType;
}
