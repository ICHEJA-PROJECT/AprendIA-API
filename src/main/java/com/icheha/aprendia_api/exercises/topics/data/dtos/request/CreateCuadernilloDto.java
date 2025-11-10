package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un cuadernillo")
public class CreateCuadernilloDto {
    
    @NotNull(message = "ID de ruta de aprendizaje es requerido")
    @Schema(description = "ID de la ruta de aprendizaje", example = "1", required = true)
    private Long idRutaAprendizaje;
    
    @NotBlank(message = "Nombre es requerido")
    @Schema(description = "Nombre del cuadernillo", example = "Cuadernillo de Alfabetización Inicial", required = true)
    private String nombre;
    
    @Schema(description = "Descripción del cuadernillo", example = "Cuadernillo para iniciar el proceso de alfabetización")
    private String descripcion;
    
    @Schema(description = "Objetivo del cuadernillo", example = "Aprender las letras del alfabeto")
    private String objetivo;
    
    @Schema(description = "URL de la imagen del cuadernillo", example = "https://example.com/image.jpg")
    private String urlImagen;
}

