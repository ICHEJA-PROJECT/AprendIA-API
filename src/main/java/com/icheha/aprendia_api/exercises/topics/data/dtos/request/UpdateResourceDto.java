package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un recurso")
public class UpdateResourceDto {

    @Schema(description = "Nombre/título del recurso (opcional)", example = "Abecedario con señas actualizado")
    private String title;

    @Schema(description = "Contenido del recurso en formato JSON (opcional)", example = "{\"material\": [...]}")
    private String content;

    @Schema(description = "ID del tema (opcional)", example = "1")
    private Long topicId;

    @Schema(description = "ID del layout (opcional)", example = "2")
    private Long layoutId;

    @Schema(description = "URL de la imagen del recurso (opcional)", example = "https://example.com/recurso.png")
    private String urlImagen;
}

