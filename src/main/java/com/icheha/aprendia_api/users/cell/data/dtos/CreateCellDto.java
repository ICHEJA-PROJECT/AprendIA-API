package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear una célula")
public class CreateCellDto {
    
    @NotNull(message = "El ID de la institución es obligatorio")
    @Schema(description = "ID de la institución", example = "1")
    private Long institutionId;
    
    @NotNull(message = "El ID del coordinador es obligatorio")
    @Schema(description = "ID de la persona coordinadora", example = "1")
    private Long coordinatorId;
    
    @NotNull(message = "La fecha de inicio es obligatoria")
    @Schema(description = "Fecha de inicio de la célula", example = "2024-01-01T00:00:00")
    private LocalDateTime startDate;
    
    @NotNull(message = "La fecha de fin es obligatoria")
    @Schema(description = "Fecha de fin de la célula", example = "2024-12-31T23:59:59")
    private LocalDateTime endDate;
}

