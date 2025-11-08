package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un rol")
public class UpdateRoleDto {
    
    @Schema(description = "Nombre del rol", example = "ESTUDIANTE")
    private String name;
    
    @Schema(description = "Descripción del rol")
    private String description;
}

