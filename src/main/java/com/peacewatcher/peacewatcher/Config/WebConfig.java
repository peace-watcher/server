package com.peacewatcher.peacewatcher.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // 허용할 오리진
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드
                .allowCredentials(false)  // 쿠키를 포함할지 여부
                .maxAge(3600);  // pre-flight 요청의 결과를 캐시할 시간(초 단위)
    }
}
