package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear una metodología")
public class CreateMetodologiaDto {
    
    @NotBlank(message = "Nombre es requerido")
    @Schema(description = "Nombre de la metodología", example = "Metodología Visual", required = true)
    private String nombre;
    
    @Schema(description = "Descripción de la metodología", example = "Enfoque educativo basado en recursos visuales y señas")
    private String descripcion;
}

