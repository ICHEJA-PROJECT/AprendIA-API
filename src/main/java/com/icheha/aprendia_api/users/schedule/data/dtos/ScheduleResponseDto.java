package com.icheha.aprendia_api.users.schedule.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Schedule")
public class ScheduleResponseDto {
    
    @Schema(description = "ID del horario")
    private Long id;
    
    @Schema(description = "Día de la semana")
    private String day;
    
    @Schema(description = "Hora")
    private String hour;
}

