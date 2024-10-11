package com.cw.littlefins_proj;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for all /api endpoints
                .allowedOrigins("*") // Replace with the correct origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed methods
                .allowCredentials(true) // Allow credentials (if needed)
                .allowedHeaders("*"); // Allow all headers
    }
}
