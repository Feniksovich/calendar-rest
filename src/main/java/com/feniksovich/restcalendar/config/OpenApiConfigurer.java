package com.feniksovich.restcalendar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурация OpenAPI/Swagger для документирования REST API.
 */
@Configuration
public class OpenApiConfigurer {

    /**
     * Регистрирует резолвер моделей OpenAPI на основе ObjectMapper.
     */
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) {
        return new ModelResolver(objectMapper);
    }

    @Bean
    public OpenAPI openAPI() {
        final Info info = new Info()
                .title("Calendar REST API")
                .description("REST API для получения григорианского календаря по номеру года")
                .version("1.0.0");

        final Server server = new Server()
                .url("http://localhost:8080")
                .description("Локальное окружение");

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
