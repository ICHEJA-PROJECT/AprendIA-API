package com.icheha.aprendia_api.users.person.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuración del módulo Person
 * Define beans necesarios para el funcionamiento del módulo
 */
@Configuration
public class PersonConfig {
    
    /**
     * Bean para realizar llamadas HTTP REST
     * Usado principalmente en ImageUploadServiceImpl para comunicarse con Cloudinary
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    // Nota: BCryptPasswordEncoder está definido en SecurityConfig como PasswordEncoder
    // para evitar duplicación de beans. Si necesitas BCryptPasswordEncoder específicamente,
    // puedes inyectar PasswordEncoder que será una instancia de BCryptPasswordEncoder.
}

