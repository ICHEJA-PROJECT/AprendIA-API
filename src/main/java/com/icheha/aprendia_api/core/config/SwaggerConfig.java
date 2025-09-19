package com.icheha.aprendia_api.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AprendIA API")
                        .version("1.0.0")
                        .description("API consolidada para la plataforma educativa AprendIA")
                        .contact(new Contact()
                                .name("AprendIA Team")
                                .email("support@aprendia.com")
                                .url("https://aprendia.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
