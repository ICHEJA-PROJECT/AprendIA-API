package com.icheha.aprendia_api.users.schedule.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un horario disponible")
public class CreateScheduleDto {
    
    @NotBlank(message = "El día es obligatorio")
    @Schema(description = "Día de la semana", example = "Lunes", 
            allowableValues = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"})
    private String day;
    
    @NotBlank(message = "La hora es obligatoria")
    @Schema(description = "Hora en formato HH:mm", example = "09:00")
    private String hour;
}

