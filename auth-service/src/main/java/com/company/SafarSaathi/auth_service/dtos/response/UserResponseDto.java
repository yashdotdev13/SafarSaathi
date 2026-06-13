package com.company.SafarSaathi.auth_service.dtos.response;


import com.company.SafarSaathi.auth_service.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private Long id;

    private String fullName;

    private String email;

    private Role role;
}
