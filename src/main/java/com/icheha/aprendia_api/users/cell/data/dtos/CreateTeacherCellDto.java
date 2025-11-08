package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para asignar un educador a una célula")
public class CreateTeacherCellDto {
    
    @NotNull(message = "El ID del educador es obligatorio")
    @Schema(description = "ID de la persona que es educador", example = "1")
    private Long teacherId;
    
    @NotNull(message = "El ID de la célula es obligatorio")
    @Schema(description = "ID de la célula", example = "1")
    private Long cellId;
}

