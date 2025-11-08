package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un estudiante")
public class CreateStudentDto {
    
    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(description = "ID de la persona", example = "1")
    private Long personId;
    
    @Schema(description = "ID del educador (opcional)", example = "2")
    private Long teacherId;
    
    @Schema(description = "Ruta del código QR (opcional)")
    private String qrPath;
    
    @NotNull(message = "El padre es obligatorio")
    @Valid
    @Schema(description = "Datos del padre")
    private CreateProgenitorDto father;
    
    @NotNull(message = "La madre es obligatoria")
    @Valid
    @Schema(description = "Datos de la madre")
    private CreateProgenitorDto mother;
    
    @Schema(description = "IDs de discapacidades del estudiante")
    private List<Long> impairments;
}

