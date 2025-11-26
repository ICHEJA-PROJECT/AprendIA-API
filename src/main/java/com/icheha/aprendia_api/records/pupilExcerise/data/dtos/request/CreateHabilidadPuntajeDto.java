package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHabilidadPuntajeDto {
    
    @NotNull(message = "El ID del ejercicio es obligatorio")
    private Long idEjercicio;
    
    @NotNull(message = "El ID de la habilidad es obligatorio")
    private Long idHabilidad;
    
    @NotNull(message = "El ID de la persona es obligatorio")
    private Long idPersona;
    
    private Long idEducando;
    
    @DecimalMin(value = "0.0", message = "El puntaje debe ser mayor o igual a 0")
    private BigDecimal puntaje;
    
    private LocalDateTime fechaCompletado;
}

