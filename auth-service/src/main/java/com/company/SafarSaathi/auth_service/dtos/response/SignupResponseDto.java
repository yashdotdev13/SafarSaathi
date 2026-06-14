package com.company.SafarSaathi.auth_service.dtos.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponseDto {

    private Long userId;
    private String fullName;
    private String email;
    private String accessToken;
}