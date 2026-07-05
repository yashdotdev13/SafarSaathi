package com.company.SafarSaathi.ai_service.tool.user.Impl;

import com.company.SafarSaathi.ai_service.tool.ToolException;
import com.company.SafarSaathi.ai_service.tool.ToolRequest;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import com.company.SafarSaathi.ai_service.tool.ToolType;
import com.company.SafarSaathi.ai_service.tool.user.UserTool;
import com.company.SafarSaathi.ai_service.tool.user.client.UserServiceClient;
import com.company.SafarSaathi.ai_service.tool.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserToolImpl implements UserTool {

    private final UserServiceClient userServiceClient;

    @Override
    public ToolType getToolType() {
        return ToolType.USER;
    }

    @Override
    public ToolResponse execute(ToolRequest request) {

        log.info("Executing User Tool.");

        try {

            UserProfileResponse profile =
                    userServiceClient.getCurrentUserProfile();

            return ToolResponse.builder()
                    .toolType(ToolType.USER)
                    .success(true)
                    .message("User profile fetched successfully.")
                    .data(profile)
                    .build();

        } catch (Exception ex) {

            log.error(
                    "Failed to fetch user profile.",
                    ex
            );

            throw new ToolException(
                    "Unable to fetch user profile.",
                    ex
            );
        }
    }
}
