package com.icheha.aprendia_api.auth.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para un usuario")
public class UserResponseDto {
    
    @Schema(description = "ID del usuario", example = "1")
    private Long id;
    
    @Schema(description = "ID de la persona asociada", example = "1")
    private Long idPersona;
    
    @Schema(description = "Nombre de usuario (username)", example = "usuario123")
    private String username;
    
    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean isActive;
    
    @Schema(description = "Fecha de creación del usuario")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de última actualización del usuario")
    private LocalDateTime updatedAt;
}

