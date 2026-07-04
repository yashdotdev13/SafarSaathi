package com.company.SafarSaathi.ai_service.tool.Impl;

import com.company.SafarSaathi.ai_service.tool.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ToolExecutorServiceImpl implements ToolExecutor {

    private final List<Tool> tools;

    private final Map<ToolType, Tool> toolRegistry =
            new EnumMap<>(ToolType.class);

    @PostConstruct
    public void initialize() {

        log.info("Initializing Tool Registry...");

        for (Tool tool : tools) {

            toolRegistry.put(
                    tool.getToolType(),
                    tool
            );

            log.info(
                    "Registered Tool: {}",
                    tool.getToolType()
            );
        }

        log.info(
                "Tool Registry initialized successfully. Registered {} tools.",
                toolRegistry.size()
        );
    }

    @Override
    public ToolResponse execute(ToolRequest request) {

        log.info(
                "Executing Tool: {}",
                request.getToolType()
        );

        Tool tool = toolRegistry.get(
                request.getToolType()
        );

        if (tool == null) {

            throw new ToolException(
                    "No tool registered for type: "
                            + request.getToolType()
            );
        }

        try {

            ToolResponse response = tool.execute(request);

            log.info(
                    "Tool executed successfully: {}",
                    request.getToolType()
            );

            return response;

        } catch (ToolException ex) {

            log.error(
                    "Tool execution failed: {}",
                    request.getToolType(),
                    ex
            );

            throw ex;

        } catch (Exception ex) {

            log.error(
                    "Unexpected error while executing tool: {}",
                    request.getToolType(),
                    ex
            );

            throw new ToolException(
                    "Failed to execute tool: "
                            + request.getToolType(),
                    ex
            );
        }
    }
}