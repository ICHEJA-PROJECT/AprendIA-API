package com.icheha.aprendia_api.preferences.region.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación ejercicio-región")
public class ExerciseRegionResponseDto {
    @Schema(description = "ID del ejercicio", example = "1")
    private Long exerciseId;

    @Schema(description = "ID de la región", example = "1")
    private Long regionId;
}

