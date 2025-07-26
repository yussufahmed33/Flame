package com.flame.flame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // تحديد المسار للصور
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/ubuntu/Flame/flame/uploads/");
    }
}
