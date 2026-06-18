package com.company.SafarSaathi.companion_service.client;

import com.company.SafarSaathi.companion_service.config.FeignConfig;
import com.company.SafarSaathi.companion_service.dtos.external.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "USER-SERVICE",
        configuration = FeignConfig.class
)
public interface UserServiceClient {

    @GetMapping("/users/profile/{userId}")
    UserProfileResponse getUserProfile(
            @PathVariable Long userId
    );
}
