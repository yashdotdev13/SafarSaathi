package com.company.SafarSaathi.ai_service.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {

            ServletRequestAttributes attributes =
                    (ServletRequestAttributes)
                            RequestContextHolder.getRequestAttributes();

            if (attributes == null) {
                return;
            }

            HttpServletRequest request = attributes.getRequest();

            copyHeader(request, requestTemplate, "Authorization");
            copyHeader(request, requestTemplate, "X-User-Id");
            copyHeader(request, requestTemplate, "X-User-Role");

        };
    }

    private void copyHeader(
            HttpServletRequest request,
            feign.RequestTemplate template,
            String header
    ) {

        String value = request.getHeader(header);

        if (value != null) {
            template.header(header, value);
        }
    }

}