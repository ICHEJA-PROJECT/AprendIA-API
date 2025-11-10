package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un tema")
public class UpdateTopicDto {

    @Schema(description = "Nombre del tema (opcional)", example = "Caligrafía Avanzada")
    private String name;

    @Schema(description = "ID del cuadernillo (opcional)", example = "1")
    private Long cuadernilloId;

    @Schema(description = "Descripción del tema (opcional)", example = "Ejercicios avanzados de caligrafía")
    private String descripcion;

    @Schema(description = "URL de la imagen del tema (opcional)", example = "https://example.com/tema.png")
    private String urlImagen;
}

