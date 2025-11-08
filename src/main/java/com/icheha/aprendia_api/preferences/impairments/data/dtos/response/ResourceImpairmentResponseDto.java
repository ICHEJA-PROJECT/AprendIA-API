package com.icheha.aprendia_api.preferences.impairments.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación recurso-discapacidad")
public class ResourceImpairmentResponseDto {
    @Schema(description = "ID del recurso", example = "1")
    private Long resourceId;

    @Schema(description = "ID de la discapacidad", example = "1")
    private Long impairmentId;
}

