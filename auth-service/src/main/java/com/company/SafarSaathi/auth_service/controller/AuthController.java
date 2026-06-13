package com.company.SafarSaathi.auth_service.controller;

import com.company.SafarSaathi.auth_service.dtos.request.LoginRequestDto;
import com.company.SafarSaathi.auth_service.dtos.request.SignupRequestDto;
import com.company.SafarSaathi.auth_service.dtos.response.LoginResponseDto;
import com.company.SafarSaathi.auth_service.dtos.response.SignupResponseDto;
import com.company.SafarSaathi.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signUp(
            @Valid @RequestBody SignupRequestDto signupRequestDto
    ) {

        SignupResponseDto response =
                authService.signUp(signupRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {

        LoginResponseDto response =
                authService.login(loginRequestDto);

        return ResponseEntity.ok(response);
    }
}