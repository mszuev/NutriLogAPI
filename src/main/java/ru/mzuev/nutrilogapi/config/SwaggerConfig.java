package ru.mzuev.nutrilogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для настройки документации Swagger.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Создает кастомную конфигурацию OpenAPI.
     *
     * @return объект OpenAPI с базовыми настройками
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(new Info()
                        .title("NutriLog API")
                        .version("v1")
                        .description("API для управления питанием и отслеживания калорий"));
    }
}