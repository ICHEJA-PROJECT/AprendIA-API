package com.icheha.aprendia_api.preferences.occupation.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación estudiante-ocupación")
public class StudentOccupationResponseDto {
    @Schema(description = "ID del estudiante", example = "1")
    private Long studentId;

    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
}
