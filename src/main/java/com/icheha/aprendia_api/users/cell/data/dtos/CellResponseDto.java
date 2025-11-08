package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Cell")
public class CellResponseDto {
    
    @Schema(description = "ID de la célula")
    private Long id;
    
    @Schema(description = "ID de la institución")
    private Long institutionId;
    
    @Schema(description = "Nombre de la institución")
    private String institutionName;
    
    @Schema(description = "ID del coordinador")
    private Long coordinatorId;
    
    @Schema(description = "Nombre del coordinador")
    private String coordinatorName;
    
    @Schema(description = "Fecha de inicio")
    private LocalDateTime startDate;
    
    @Schema(description = "Fecha de fin")
    private LocalDateTime endDate;
}

