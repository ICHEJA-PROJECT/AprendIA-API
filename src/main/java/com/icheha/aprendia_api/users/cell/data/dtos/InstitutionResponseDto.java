package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Institution")
public class InstitutionResponseDto {
    
    @Schema(description = "ID de la institución")
    private Long id;
    
    @Schema(description = "RFC de la institución")
    private String rfc;
    
    @Schema(description = "RCT de la institución")
    private String rct;
    
    @Schema(description = "Nombre de la institución")
    private String name;
}

