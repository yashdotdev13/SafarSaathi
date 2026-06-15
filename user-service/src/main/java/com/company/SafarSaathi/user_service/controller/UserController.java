package com.company.SafarSaathi.user_service.controller;

import com.company.SafarSaathi.user_service.dtos.request.UpdateUserProfileRequest;
import com.company.SafarSaathi.user_service.dtos.response.UserProfileResponse;
import com.company.SafarSaathi.user_service.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        log.info("Fetching current user profile");

        return ResponseEntity.ok(
                userService.getCurrentUserProfile()
        );
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @Valid @RequestBody UpdateUserProfileRequest request
    ) {
        log.info("Updating current user profile");

        return ResponseEntity.ok(
                userService.updateUser(request)
        );
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfileById(
            @Positive(message = "User ID must be positive")
            @PathVariable Long userId
    ) {

        log.info("Fetching profile for userId={}", userId);
        return ResponseEntity.ok(
                userService.getUserById(userId)
        );
    }
}