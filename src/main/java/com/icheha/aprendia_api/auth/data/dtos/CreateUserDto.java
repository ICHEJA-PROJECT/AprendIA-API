package com.icheha.aprendia_api.auth.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un usuario")
public class CreateUserDto {
    
    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(description = "ID de la persona asociada al usuario", example = "1", required = true)
    private Long idPersona;
    
    @NotBlank(message = "El username es obligatorio")
    @Size(min = 3, max = 100, message = "El username debe tener entre 3 y 100 caracteres")
    @Schema(description = "Nombre de usuario (username)", example = "usuario123", required = true)
    private String username;
    
    @Size(min = 6, max = 255, message = "La contraseña debe tener entre 6 y 255 caracteres")
    @Schema(
        description = "Contraseña del usuario (OPCIONAL). Si no se proporciona, el usuario se creará sin contraseña y deberá establecerla posteriormente.", 
        example = "password123", 
        required = false
    )
    private String password;
    
    @Schema(description = "Indica si el usuario está activo", example = "true")
    private Boolean isActive = true;
}

