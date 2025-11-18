package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Pariente")
public class ParienteResponseDto {
    
    @Schema(description = "ID del pariente")
    private Long id;
    
    @Schema(description = "ID de la persona (estudiante)")
    private Long personaId;
    
    @Schema(description = "ID del pariente")
    private Long parienteId;
    
    @Schema(description = "ID del rol de pariente")
    private Long rolParienteId;
    
    @Schema(description = "Nombre del rol de pariente")
    private String rolParienteNombre;
    
    @Schema(description = "Nombre completo del pariente")
    private String parienteNombreCompleto;
    
    @Schema(description = "CURP del pariente")
    private String parienteCurp;
}

