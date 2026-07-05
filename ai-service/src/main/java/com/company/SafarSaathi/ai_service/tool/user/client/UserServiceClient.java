package com.company.SafarSaathi.ai_service.tool.user.client;

import com.company.SafarSaathi.ai_service.tool.user.dto.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "user-service"
)
public interface UserServiceClient {

    @GetMapping("/users/profile")
    UserProfileResponse getCurrentUserProfile();

}