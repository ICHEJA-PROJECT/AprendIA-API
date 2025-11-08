package com.icheha.aprendia_api.preferences.impairments.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación reactivo-discapacidad")
public class ReactiveImpairmentResponseDto {
    @Schema(description = "ID del reactivo", example = "1")
    private Long reactiveId;

    @Schema(description = "ID de la discapacidad", example = "1")
    private Long impairmentId;
}

