package com.ssafy.sandbox;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://ssafysandbox.vercel.app", "https://ssafysandbox.vercel.app/paging") // 특정 출처만 허용
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTION")
                .allowedHeaders("Authorization", "Content-Type")
                .exposedHeaders("Custom-Header")
                .allowCredentials(true) // credentials 설정을 사용
                .maxAge(3600);
    }

}
// https://velog.io/@yoonuk/Spring-Boot-CORS-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
