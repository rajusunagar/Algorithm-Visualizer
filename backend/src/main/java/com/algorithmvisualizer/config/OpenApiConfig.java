package com.algorithmvisualizer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI algorithmVisualizerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Algorithm Visualizer API")
                        .description("REST API for algorithm visualization")
                        .version("1.0.0"));
    }
}