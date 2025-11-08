package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para TeacherCell")
public class TeacherCellResponseDto {
    
    @Schema(description = "ID del educador")
    private Long teacherId;
    
    @Schema(description = "Nombre del educador")
    private String teacherName;
    
    @Schema(description = "ID de la célula")
    private Long cellId;
}

