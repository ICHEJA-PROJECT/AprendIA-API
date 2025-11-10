package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Metodología")
public class MetodologiaResponseDto {
    
    @Schema(description = "ID de la metodología")
    private Long id;
    
    @Schema(description = "Nombre de la metodología")
    private String nombre;
    
    @Schema(description = "Descripción de la metodología")
    private String descripcion;
    
    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de actualización")
    private LocalDateTime updateAt;
}

