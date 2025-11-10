package com.icheha.aprendia_api.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${swagger.server.url:http://localhost:8080}")
    private String swaggerServerUrl;

    @Value("${swagger.server.production.url:}")
    private String swaggerProductionUrl;

    @Value("${server.servlet.context-path:/api}")
    private String contextPath;

    @Bean
    public OpenAPI customOpenAPI() {
        // Construir la URL base usando la configuración de Swagger o el puerto del servidor
        String baseUrl = swaggerServerUrl + contextPath;
        
        // Construir lista de servidores
        List<Server> servers = new java.util.ArrayList<>();
        
        // Servidor de desarrollo/actual (siempre presente)
        servers.add(new Server()
                .url(baseUrl)
                .description("Servidor actual"));
        
        // Servidor de producción (solo si está configurado)
        if (swaggerProductionUrl != null && !swaggerProductionUrl.trim().isEmpty()) {
            String productionUrl = swaggerProductionUrl.endsWith(contextPath) 
                    ? swaggerProductionUrl 
                    : swaggerProductionUrl + contextPath;
            servers.add(new Server()
                    .url(productionUrl)
                    .description("Servidor de producción"));
        }
        
        return new OpenAPI()
                .info(new Info()
                        .title("AprendIA API")
                        .description("API REST para el sistema de aprendizaje adaptativo AprendIA. " +
                                "Los endpoints están organizados según el flujo del programa: " +
                                "1) Autenticación, 2) Gestión de Usuarios, 3) Preferencias, " +
                                "4) Contenido Educativo, 5) Registros de Progreso, 6) Recursos Multimedia.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo AprendIA")
                                .email("contacto@aprendia.com")
                                .url("https://aprendia.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(servers)
                .tags(getOrderedTags());
    }
    
    /**
     * Define los tags en el orden del flujo del programa
     * El orden es importante para la organización en Swagger UI
     */
    private List<Tag> getOrderedTags() {
        return Arrays.asList(
                // 1. AUTENTICACIÓN
                new Tag().name("1. Authentication").description("Autenticación y autorización de usuarios (login, validación de tokens)"),
                
                // 2. GESTIÓN DE USUARIOS
                new Tag().name("2.01. Persons").description("Gestión de personas (usuarios base del sistema)"),
                new Tag().name("2.02. Roles").description("Gestión de roles del sistema"),
                new Tag().name("2.03. Role Persons").description("Asignación de roles a personas"),
                new Tag().name("2.04. Students").description("Gestión de estudiantes (educandos)"),
                new Tag().name("2.05. Cells").description("Gestión de células educativas"),
                new Tag().name("2.06. Institutions").description("Gestión de instituciones educativas"),
                new Tag().name("2.07. Teacher Cells").description("Relaciones educador-célula"),
                new Tag().name("2.08. Schedules").description("Gestión de horarios disponibles"),
                new Tag().name("2.09. Schedule Persons").description("Asignación de horarios a personas"),
                new Tag().name("2.10. Road Types").description("Gestión de tipos de vialidad"),
                new Tag().name("2.11. Settlements").description("Gestión de asentamientos"),
                new Tag().name("2.12. Towns").description("Gestión de ciudades"),
                new Tag().name("2.13. Municipalities").description("Gestión de municipios"),
                
                // 3. PREFERENCIAS
                new Tag().name("3.01. Impairments").description("Gestión de discapacidades"),
                new Tag().name("3.02. Student Impairments").description("Asignación de discapacidades a estudiantes"),
                new Tag().name("3.03. Learning Path Impairment Management").description("Rutas de aprendizaje por discapacidad"),
                new Tag().name("3.04. Resource Impairment Management").description("Recursos adaptados por discapacidad"),
                new Tag().name("3.05. Reactive Impairment Management").description("Plantillas adaptadas por discapacidad"),
                new Tag().name("3.06. Occupation Management").description("Gestión de ocupaciones"),
                new Tag().name("3.07. Student Occupation Management").description("Asignación de ocupaciones a estudiantes"),
                new Tag().name("3.08. Exercise-Occupation Management").description("Ejercicios personalizados por ocupación"),
                new Tag().name("3.09. Regions").description("Gestión de regiones geográficas"),
                new Tag().name("3.10. Student Region Management").description("Asignación de regiones a estudiantes"),
                new Tag().name("3.11. Exercise Region Management").description("Ejercicios adaptados por región"),
                new Tag().name("3.12. Words").description("Gestión de vocabulario"),
                new Tag().name("3.13. Word Meanings").description("Significados de palabras"),
                new Tag().name("3.14. Word Occupation Management").description("Relaciones palabra-ocupación"),
                new Tag().name("3.15. Word Region Management").description("Relaciones palabra-región"),
                new Tag().name("3.16. Preferences").description("Endpoints generales de preferencias"),
                
                // 4. CONTENIDO EDUCATIVO - Ordenado según flujo del programa
                new Tag().name("4.01. Metodologías").description("Gestión de metodologías educativas"),
                new Tag().name("4.02. Learning Paths").description("Gestión de rutas de aprendizaje"),
                new Tag().name("4.03. Cuadernillos").description("Gestión de cuadernillos"),
                new Tag().name("4.04. Topics").description("Gestión de temas"),
                new Tag().name("4.05. Resources").description("Gestión de recursos educativos"),
                new Tag().name("4.06. Templates").description("Gestión de plantillas de ejercicios (Reactivos)"),
                new Tag().name("4.07. Topic Resources").description("Recursos asociados a temas"),
                new Tag().name("4.08. Topic Sequences").description("Secuencias de temas para rutas de aprendizaje"),
                new Tag().name("4.09. Type Layout").description("Tipos de layouts"),
                new Tag().name("4.10. Layouts").description("Gestión de layouts de presentación"),
                new Tag().name("4.11. Skills").description("Gestión de habilidades"),
                new Tag().name("4.12. Template Skills").description("Habilidades asociadas a plantillas"),
                new Tag().name("4.13. Type Instruction Media").description("Tipos de instrucciones y medios"),
                new Tag().name("4.14. Template Instruction Media").description("Instrucciones asociadas a plantillas"),
                new Tag().name("4.15. Exercises").description("Gestión de ejercicios individuales"),
                new Tag().name("4.16. Units").description("Gestión de unidades educativas"),
                
                // 5. REGISTROS DE PROGRESO
                new Tag().name("5.1. Pupil Exercises").description("Registro de ejercicios completados por estudiantes"),
                new Tag().name("5.2. Pupil Skills").description("Registro de habilidades y porcentajes de dominio"),
                new Tag().name("5.3. Pupil Topics").description("Registro de temas completados"),
                
                // 6. RECURSOS MULTIMEDIA
                new Tag().name("6.1. Assets").description("Gestión de recursos multimedia"),
                new Tag().name("6.2. Tags").description("Gestión de etiquetas para recursos")
        );
    }
}