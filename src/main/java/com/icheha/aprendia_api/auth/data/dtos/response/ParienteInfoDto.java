package com.icheha.aprendia_api.auth.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParienteInfoDto {
    /**
     * Indica si el pariente existe (true) o no (false)
     */
    private Boolean existe;
    
    /**
     * ID de la persona que es el pariente
     */
    private Long idPersona;
    
    /**
     * Primer nombre del pariente
     */
    private String primerNombre;
    
    /**
     * Segundo nombre del pariente
     */
    private String segundoNombre;
    
    /**
     * Primer apellido del pariente
     */
    private String primerApellido;
    
    /**
     * Segundo apellido del pariente
     */
    private String segundoApellido;
    
    /**
     * Nombre completo del pariente (primer nombre + segundo nombre + apellidos)
     */
    private String nombreCompleto;
    
    /**
     * CURP del pariente
     */
    private String curp;
    
    /**
     * Email del pariente
     */
    private String email;
    
    /**
     * Teléfono del pariente
     */
    private String telefono;
    
    /**
     * Fecha de nacimiento del pariente
     */
    private LocalDate fechaNacimiento;
    
    /**
     * Género del pariente (M, F, etc.)
     */
    private String genero;
    
    /**
     * Número de INE del pariente
     */
    private String numeroIne;
    
    /**
     * Ruta de la imagen de perfil del pariente
     */
    private String profileImagePath;
}

