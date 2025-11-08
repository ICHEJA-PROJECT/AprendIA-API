package com.icheha.aprendia_api.users.person.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Persona")
public class PersonResponseDto {
    
    @Schema(description = "ID de la persona")
    private Long idPersona;
    
    @Schema(description = "Primer nombre")
    private String primerNombre;
    
    @Schema(description = "Segundo nombre")
    private String segundoNombre;
    
    @Schema(description = "Apellido paterno")
    private String apellidoPaterno;
    
    @Schema(description = "Apellido materno")
    private String apellidoMaterno;
    
    @Schema(description = "CURP")
    private String curp;
    
    @Schema(description = "Número de INE")
    private String numeroIne;
    
    @Schema(description = "Fecha de nacimiento")
    private LocalDate fechaNacimiento;
    
    @Schema(description = "Género")
    private String genero;
    
    @Schema(description = "Nombre de la vialidad")
    private String vialidadNombre;
    
    @Schema(description = "Ruta de la imagen de perfil")
    private String profileImagePath;
}

