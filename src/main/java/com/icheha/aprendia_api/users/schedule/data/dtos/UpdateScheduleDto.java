package com.icheha.aprendia_api.users.schedule.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un horario")
public class UpdateScheduleDto {

    @Schema(description = "Día de la semana (opcional)", example = "Martes",
            allowableValues = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"})
    private String day;

    @Schema(description = "Hora en formato HH:mm (opcional)", example = "10:00")
    private String hour;
}

