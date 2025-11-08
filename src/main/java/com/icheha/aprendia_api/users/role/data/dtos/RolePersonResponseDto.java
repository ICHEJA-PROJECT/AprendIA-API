package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para PersonaRol")
public class RolePersonResponseDto {
    
    @Schema(description = "ID de la relación persona-rol")
    private Long id;
    
    @Schema(description = "ID de la persona")
    private Long personId;
    
    @Schema(description = "ID del rol")
    private Long roleId;
    
    @Schema(description = "Nombre del rol")
    private String roleName;
}

