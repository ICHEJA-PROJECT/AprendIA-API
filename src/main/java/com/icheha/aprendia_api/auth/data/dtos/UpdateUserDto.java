package com.icheha.aprendia_api.auth.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un usuario")
public class UpdateUserDto {
    
    @Size(min = 3, max = 100, message = "El username debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre de usuario (username)", example = "usuario_actualizado")
    private String username;
    
    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean isActive;
}

