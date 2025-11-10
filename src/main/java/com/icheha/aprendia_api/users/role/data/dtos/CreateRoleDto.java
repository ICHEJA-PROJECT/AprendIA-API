package com.icheha.aprendia_api.users.role.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un rol")
public class CreateRoleDto {
    
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Schema(description = "Nombre del rol", example = "ESTUDIANTE")
    private String name;
}

