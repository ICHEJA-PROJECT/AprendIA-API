package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabilidadPuntajeResponseDto {
    
    private Long idHabilidadPuntaje;
    private Long idEjercicio;
    private Long idHabilidad;
    private Long idPersona;
    private Long idEducando;
    private BigDecimal puntaje;
    private LocalDateTime fechaCompletado;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    
    // Información adicional de las relaciones (opcional)
    private String ejercicioNombre;
    private String habilidadNombre;
    private String usuarioNombre;
}

