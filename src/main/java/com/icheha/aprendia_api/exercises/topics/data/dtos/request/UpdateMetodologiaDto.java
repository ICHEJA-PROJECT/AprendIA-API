package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una metodología")
public class UpdateMetodologiaDto {
    
    @Schema(description = "Nombre de la metodología (opcional)", example = "Metodología Visual")
    private String nombre;
    
    @Schema(description = "Descripción de la metodología (opcional)", example = "Enfoque educativo basado en recursos visuales y señas")
    private String descripcion;
}

