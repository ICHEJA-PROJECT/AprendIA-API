package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Student")
public class StudentResponseDto {
    
    @Schema(description = "ID del estudiante")
    private Long id;
    
    @Schema(description = "ID de la persona")
    private Long personId;
    
    @Schema(description = "ID del educador (opcional)")
    private Long teacherId;
    
    @Schema(description = "Ruta del código QR")
    private String qrPath;
    
    @Schema(description = "ID del padre")
    private Long fatherId;
    
    @Schema(description = "ID de la madre")
    private Long motherId;
}

