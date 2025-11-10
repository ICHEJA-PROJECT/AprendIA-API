package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para Cuadernillo")
public class CuadernilloResponseDto {
    
    @Schema(description = "ID del cuadernillo")
    private Long id;
    
    @Schema(description = "ID de la ruta de aprendizaje")
    private Long idRutaAprendizaje;
    
    @Schema(description = "Nombre del cuadernillo")
    private String nombre;
    
    @Schema(description = "Descripción del cuadernillo")
    private String descripcion;
    
    @Schema(description = "Objetivo del cuadernillo")
    private String objetivo;
    
    @Schema(description = "URL de la imagen del cuadernillo")
    private String urlImagen;
    
    @Schema(description = "Fecha de creación")
    private LocalDateTime createdAt;
    
    @Schema(description = "Fecha de actualización")
    private LocalDateTime updateAt;
}

