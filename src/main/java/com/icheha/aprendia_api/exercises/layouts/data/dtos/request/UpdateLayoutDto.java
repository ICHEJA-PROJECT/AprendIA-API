package com.icheha.aprendia_api.exercises.layouts.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un layout")
public class UpdateLayoutDto {

    @Schema(description = "Nombre del layout (opcional)", example = "Layout Actualizado")
    private String name;

    @Schema(description = "Atributos/descripción del layout (opcional)", example = "Atributos actualizados")
    private String attributes;

    @Schema(description = "Descripción del layout (opcional)", example = "Descripción actualizada")
    private String description;

    @Schema(description = "URL de la imagen del layout (opcional)", example = "https://example.com/layout.png")
    private String urlImage;

    @Schema(description = "Indica si el layout está activo (opcional)", example = "true")
    private Boolean isActive;

    @Schema(description = "ID del tipo de layout (opcional)", example = "1")
    private Long typeLayoutId;
}

