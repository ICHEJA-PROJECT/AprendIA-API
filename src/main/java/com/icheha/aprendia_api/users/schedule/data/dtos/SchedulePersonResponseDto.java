package com.icheha.aprendia_api.users.schedule.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para SchedulePerson")
public class SchedulePersonResponseDto {
    
    @Schema(description = "ID de la relación persona-rol")
    private Long rolePersonId;
    
    @Schema(description = "ID del horario")
    private Long scheduleId;
    
    @Schema(description = "ID de la persona")
    private Long personId;
    
    @Schema(description = "ID del rol")
    private Long roleId;
    
    @Schema(description = "Día del horario")
    private String day;
    
    @Schema(description = "Hora del horario")
    private String hour;
}

