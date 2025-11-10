package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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
    
    @Valid
    @Schema(description = "Datos del padre (opcional)")
    private CreateProgenitorDto father;
    
    @Valid
    @Schema(description = "Datos de la madre (opcional)")
    private CreateProgenitorDto mother;
    
    @Schema(description = "IDs de discapacidades del estudiante (opcional)")
    private List<Long> impairments;
}

