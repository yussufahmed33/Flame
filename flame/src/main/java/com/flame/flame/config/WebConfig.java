package com.flame.flame.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // تحديد المسار للصور
        registry.addResourceHandler("/uploads/**")
          //      .addResourceLocations("file:/C:/Users/yussuf ahmed/Documents/GitHub/flame/flame/uploads/");

          .addResourceLocations("file:/home/ubuntu/Flame/flame/uploads/");
    }

    @Configuration
    public class ThymeleafConfig {

        @Bean
        public Java8TimeDialect java8TimeDialect() {
            return new Java8TimeDialect();
        }

    }

}
