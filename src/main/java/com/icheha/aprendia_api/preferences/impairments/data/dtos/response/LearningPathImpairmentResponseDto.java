package com.icheha.aprendia_api.preferences.impairments.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación ruta de aprendizaje-discapacidad")
public class LearningPathImpairmentResponseDto {
    @Schema(description = "ID de la ruta de aprendizaje", example = "1")
    private Long learningPathId;

    @Schema(description = "ID de la discapacidad", example = "1")
    private Long impairmentId;
}

