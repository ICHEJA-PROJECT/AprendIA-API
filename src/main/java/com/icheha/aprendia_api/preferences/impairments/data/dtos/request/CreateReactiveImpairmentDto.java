package com.icheha.aprendia_api.preferences.impairments.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación reactivo-discapacidad")
public class CreateReactiveImpairmentDto {
    @NotNull(message = "El ID del reactivo es obligatorio")
    @Schema(description = "ID del reactivo", example = "1")
    private Long reactiveId;

    @NotNull(message = "El ID de la discapacidad es obligatorio")
    @Schema(description = "ID de la discapacidad", example = "1")
    private Long impairmentId;
}

