package com.icheha.aprendia_api.preferences.occupation.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear una relación estudiante-ocupación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación estudiante-ocupación")
public class CreateStudentOccupationDto {
    
    @NotNull(message = "El ID del estudiante es obligatorio")
    @Schema(description = "ID del estudiante", example = "1")
    private Long studentId;
    
    @NotNull(message = "El ID de la ocupación es obligatorio")
    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
}
