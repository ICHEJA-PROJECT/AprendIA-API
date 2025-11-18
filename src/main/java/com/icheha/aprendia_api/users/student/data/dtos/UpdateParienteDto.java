package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un pariente")
public class UpdateParienteDto {
    
    @NotNull(message = "El ID de la persona (estudiante) es obligatorio")
    @Schema(description = "ID de la persona (estudiante)", example = "1")
    private Long personaId;
    
    @NotNull(message = "El ID del pariente es obligatorio")
    @Schema(description = "ID del pariente (otra persona)", example = "2")
    private Long parienteId;
    
    @NotNull(message = "El ID del rol de pariente es obligatorio")
    @Schema(description = "ID del rol de pariente", example = "1")
    private Long rolParienteId;
}

