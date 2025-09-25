package com.icheha.aprendia_api.preferences.occupation.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear una relación ejercicio-ocupación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación ejercicio-ocupación")
public class CreateExerciseOccupationDto {
    
    @NotNull(message = "El ID del ejercicio es obligatorio")
    @Schema(description = "ID del ejercicio", example = "1")
    private Long exerciseId;
    
    @NotNull(message = "El ID de la ocupación es obligatorio")
    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
}
