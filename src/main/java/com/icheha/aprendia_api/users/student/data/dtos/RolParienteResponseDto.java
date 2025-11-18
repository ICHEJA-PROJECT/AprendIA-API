package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para RolPariente")
public class RolParienteResponseDto {
    
    @Schema(description = "ID del rol de pariente")
    private Long id;
    
    @Schema(description = "Nombre del rol")
    private String nombre;
}

