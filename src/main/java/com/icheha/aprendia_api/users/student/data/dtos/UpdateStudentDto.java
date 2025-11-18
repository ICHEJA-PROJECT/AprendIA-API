package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un estudiante")
public class UpdateStudentDto {
    
    @Schema(description = "ID del educador (opcional)", example = "2")
    private Long teacherId;
    
    @Schema(description = "Ruta del código QR (opcional)")
    private String qrPath;
    
    @Schema(description = "ID de la persona que es el padre (opcional)", example = "2")
    private Long fatherPersonId;
    
    @Schema(description = "ID de la persona que es la madre (opcional)", example = "3")
    private Long motherPersonId;
    
    @Schema(description = "IDs de discapacidades del estudiante (opcional)")
    private List<Long> impairments;
}

