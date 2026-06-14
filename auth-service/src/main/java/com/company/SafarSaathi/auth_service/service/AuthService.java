package com.company.SafarSaathi.auth_service.service;

import com.company.SafarSaathi.auth_service.dtos.request.LoginRequestDto;
import com.company.SafarSaathi.auth_service.dtos.request.SignupRequestDto;
import com.company.SafarSaathi.auth_service.dtos.response.LoginResponseDto;
import com.company.SafarSaathi.auth_service.dtos.response.SignupResponseDto;
import com.company.SafarSaathi.auth_service.entities.User;
import com.company.SafarSaathi.auth_service.enums.Role;
import com.company.SafarSaathi.auth_service.exceptions.InvalidCredentialsException;
import com.company.SafarSaathi.auth_service.exceptions.ResourceNotFoundException;
import com.company.SafarSaathi.auth_service.exceptions.UserAlreadyExistsException;
import com.company.SafarSaathi.auth_service.repository.UserRepository;
import com.company.SafarSaathi.auth_service.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public SignupResponseDto signUp(SignupRequestDto signupRequestDto) {

        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new UserAlreadyExistsException(
                    "User already exists with email: "
                            + signupRequestDto.getEmail()
            );
        }

        User user = User.builder()
                .fullName(signupRequestDto.getFullName())
                .email(signupRequestDto.getEmail())
                .password(
                        PasswordUtils.hashPassword(
                                signupRequestDto.getPassword()
                        )
                )
                .role(Role.TRAVELLER)
                .build();

        User savedUser = userRepository.save(user);

        String accessToken =
                jwtService.generateAccessToken(savedUser);

        log.info(
                "User registered successfully with email: {}",
                savedUser.getEmail()
        );

        return SignupResponseDto.builder()
                .userId(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .accessToken(accessToken)
                .build();
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(
                        loginRequestDto.getEmail()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: "
                                        + loginRequestDto.getEmail()
                        )
                );

        if (!PasswordUtils.checkPassword(
                loginRequestDto.getPassword(),
                user.getPassword()
        )) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        String accessToken =
                jwtService.generateAccessToken(user);

        log.info(
                "User logged in successfully with email: {}",
                user.getEmail()
        );

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }
}
