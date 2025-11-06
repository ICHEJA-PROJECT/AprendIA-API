package com.icheha.aprendia_api.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url:http://localhost:8080}")
    private String swaggerServerUrl;

    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenAPI() {
        // Construir la URL base usando la configuración de Swagger o el puerto del servidor
        String baseUrl = swaggerServerUrl + contextPath;
        
        return new OpenAPI()
                .info(new Info()
                        .title("AprendIA API")
                        .description("API REST para el sistema de aprendizaje adaptativo AprendIA. " +
                                "Proporciona endpoints para gestión de ejercicios, temas, plantillas, " +
                                "recursos, unidades, layouts, habilidades, preferencias y registros de estudiantes.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo AprendIA")
                                .email("contacto@aprendia.com")
                                .url("https://aprendia.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url(baseUrl)
                                .description("Servidor de desarrollo"),
                        new Server()
                                .url("https://api.aprendia.com/api")
                                .description("Servidor de producción")
                ));
    }
}