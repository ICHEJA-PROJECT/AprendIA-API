package com.icheha.aprendia_api.preferences.region.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación estudiante-región")
public class StudentRegionResponseDto {
    @Schema(description = "ID del estudiante", example = "1")
    private Long studentId;

    @Schema(description = "ID de la región", example = "1")
    private Long regionId;
}

