package com.icheha.aprendia_api.preferences.occupation.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear una nueva ocupación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una nueva ocupación")
public class CreateOccupationDto {
    
    @NotBlank(message = "El nombre de la ocupación es obligatorio")
    @Size(max = 32, message = "El nombre de la ocupación no puede exceder 32 caracteres")
    @Schema(description = "Nombre de la ocupación", example = "Ingeniero de Software", maxLength = 32)
    private String name;
}

