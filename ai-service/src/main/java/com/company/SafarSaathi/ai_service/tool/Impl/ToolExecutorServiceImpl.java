package com.company.SafarSaathi.ai_service.tool.Impl;


import com.company.SafarSaathi.ai_service.tool.*;
import com.company.SafarSaathi.ai_service.tool.exception.ToolExecutionException;
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
    private final Map<ToolType, Tool> toolRegistry = new EnumMap<>(ToolType.class);

    @PostConstruct
    public void initialize(){
        tools.forEach(tool -> {
            toolRegistry.put(tool.getToolType(), tool);

        log.info("Registered tool: {}",tool.getToolType());

        });
    }


    @Override
    public ToolResponse execute(ToolRequest request) {


        Tool tool = toolRegistry.get(request.getToolType());

        if(tool == null){
            throw new ToolExecutionException("No tool registered for type: "+request.getToolType());
        }
        log.info("Executing tool: {}",request.getToolType());
        return tool.execute(request);
    }
}
