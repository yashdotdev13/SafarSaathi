package com.company.SafarSaathi.ai_service.planner.context.Impl;


import com.company.SafarSaathi.ai_service.planner.context.ContextMerger;
import com.company.SafarSaathi.ai_service.tool.ToolResponse;
import com.company.SafarSaathi.ai_service.tool.formatter.ToolResultFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextManagerImpl implements ContextMerger {

    private final ToolResultFormatter formatter;


    @Override
    public String merge(List<ToolResponse> responses) {

        StringBuilder builder = new StringBuilder();

        for (ToolResponse response : responses) {

            builder.append("\n");
            builder.append("Tool: ")
                    .append(response.getToolType())
                    .append("\n");

            builder.append(
                    formatter.format(
                            response.getData()
                    )
            );

            builder.append("\n");
        }

        return builder.toString();
    }
}
