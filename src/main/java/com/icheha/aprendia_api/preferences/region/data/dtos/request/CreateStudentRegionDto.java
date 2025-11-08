package com.icheha.aprendia_api.preferences.region.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación estudiante-región")
public class CreateStudentRegionDto {
    @NotNull(message = "El ID del estudiante es obligatorio")
    @Schema(description = "ID del estudiante", example = "1")
    private Long studentId;

    @NotNull(message = "El ID de la región es obligatorio")
    @Schema(description = "ID de la región", example = "1")
    private Long regionId;
}

