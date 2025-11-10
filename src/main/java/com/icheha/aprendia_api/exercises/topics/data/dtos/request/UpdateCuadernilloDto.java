package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un cuadernillo")
public class UpdateCuadernilloDto {
    
    @Schema(description = "ID de la ruta de aprendizaje (opcional)", example = "1")
    private Long idRutaAprendizaje;
    
    @Schema(description = "Nombre del cuadernillo (opcional)", example = "Cuadernillo de Alfabetización Inicial")
    private String nombre;
    
    @Schema(description = "Descripción del cuadernillo (opcional)", example = "Cuadernillo para iniciar el proceso de alfabetización")
    private String descripcion;
    
    @Schema(description = "Objetivo del cuadernillo (opcional)", example = "Aprender las letras del alfabeto")
    private String objetivo;
    
    @Schema(description = "URL de la imagen del cuadernillo (opcional)", example = "https://example.com/image.jpg")
    private String urlImagen;
}

