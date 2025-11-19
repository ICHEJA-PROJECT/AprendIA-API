package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un estudiante en el sistema")
public class CreateStudentDto {
    
    @NotNull(message = "El ID de la persona es obligatorio")
    @Schema(
        description = "ID de la persona que será el estudiante", 
        example = "1", 
        required = true
    )
    private Long personId;
    
    @Schema(
        description = "ID del educador asignado al estudiante (OPCIONAL). Si no se proporciona, el estudiante se creará sin educador asignado.", 
        example = "2", 
        required = false
    )
    private Long teacherId;
    
    @Schema(
        description = "Lista de IDs de discapacidades del estudiante (OPCIONAL). Si no se proporciona, el estudiante se creará sin discapacidades registradas.", 
        example = "[1, 2]", 
        required = false
    )
    private List<Long> impairments;
}

