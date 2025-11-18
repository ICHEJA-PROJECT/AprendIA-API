package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un rol de pariente")
public class UpdateRolParienteDto {
    
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Schema(description = "Nombre del rol de pariente", example = "Padre")
    private String nombre;
}

