package com.company.SafarSaathi.companion_service.client;

import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "USER-SERVICE"
)
public interface UserServiceClient {

    @GetMapping("/users/profile/{userId}")
    UserProfileResponse getUserById(
            @PathVariable Long userId
    );
}
