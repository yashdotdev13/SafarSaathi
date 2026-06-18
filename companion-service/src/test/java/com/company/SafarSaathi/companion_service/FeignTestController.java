package com.company.SafarSaathi.companion_service;

import com.company.SafarSaathi.companion_service.client.UserServiceClient;
import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class FeignTestController {

    private final UserServiceClient userServiceClient;

    @GetMapping("/{userId}")
    public UserProfileResponse test(
            @PathVariable Long userId
    ) {
        return userServiceClient.getUserById(userId);
    }
}