package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una célula")
public class UpdateCellDto {

    @Schema(description = "ID de la institución (opcional)", example = "1")
    private Long institutionId;

    @Schema(description = "ID del coordinador (opcional)", example = "1")
    private Long coordinatorId;

    @Schema(description = "Fecha de inicio de la célula (opcional)", example = "2024-01-01T00:00:00")
    private LocalDateTime startDate;

    @Schema(description = "Fecha de fin de la célula (opcional)", example = "2024-12-31T23:59:59")
    private LocalDateTime endDate;
}

