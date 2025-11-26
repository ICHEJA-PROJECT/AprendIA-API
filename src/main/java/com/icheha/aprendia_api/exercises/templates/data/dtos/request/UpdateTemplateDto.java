package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un template/reactivo")
public class UpdateTemplateDto {

    @Schema(description = "Título del template (opcional)", example = "Aprendiendo a hacer Líneas Rectas Horizontales - Actualizado")
    private String title;

    @Schema(description = "Instrucciones del template (opcional)", example = "Desliza tu lápiz de izquierda a derecha...")
    private String instructions;

    @Schema(description = "Tiempo sugerido en minutos (opcional)", example = "15")
    private Integer suggestTime;

    @Schema(description = "ID del layout (opcional)", example = "1")
    private Long layoutId;

    @Schema(description = "ID del tema (opcional)", example = "2")
    private Long topicId;

    @Schema(description = "ID del recurso (opcional)", example = "1")
    private Long resourceId;

    @Schema(description = "URL de la imagen del template (opcional)", example = "https://example.com/template.png")
    private String urlImagen;
}

