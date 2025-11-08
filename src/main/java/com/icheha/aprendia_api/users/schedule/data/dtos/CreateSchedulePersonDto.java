package com.icheha.aprendia_api.users.schedule.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para asignar un horario a una persona con rol")
public class CreateSchedulePersonDto {
    
    @NotNull(message = "El ID de la relación persona-rol es obligatorio")
    @Schema(description = "ID de la relación entre persona y rol", example = "1")
    private Long rolePersonId;
    
    @NotNull(message = "El ID del horario es obligatorio")
    @Schema(description = "ID del horario disponible", example = "1")
    private Long scheduleId;
}

