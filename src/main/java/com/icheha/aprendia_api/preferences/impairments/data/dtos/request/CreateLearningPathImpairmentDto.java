package com.icheha.aprendia_api.preferences.impairments.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación ruta de aprendizaje-discapacidad")
public class CreateLearningPathImpairmentDto {
    @NotNull(message = "El ID de la ruta de aprendizaje es obligatorio")
    @Schema(description = "ID de la ruta de aprendizaje", example = "1")
    private Long learningPathId;

    @NotNull(message = "El ID de la discapacidad es obligatorio")
    @Schema(description = "ID de la discapacidad", example = "1")
    private Long impairmentId;
}

