package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Rol")
public class RoleResponseDto {
    
    @Schema(description = "ID del rol")
    private Long id;
    
    @Schema(description = "Nombre del rol")
    private String name;
    
    @Schema(description = "Descripción del rol")
    private String description;
}

