package com.rsing.recipe.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Recipe Management Springboot API",
                version = "1.0",
                description = "API documentation for your application"
        )
)
public class SwaggerConfig { }
