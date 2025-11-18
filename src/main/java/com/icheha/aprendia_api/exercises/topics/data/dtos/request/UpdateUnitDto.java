package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una unidad")
public class UpdateUnitDto {

    @Schema(description = "Nombre de la unidad (opcional)", example = "Unidad 1 - Actualizada")
    private String name;

    @Schema(description = "Descripción de la unidad (opcional)", example = "Descripción actualizada")
    private String description;

    @Schema(description = "ID del cuadernillo (opcional)", example = "1")
    private Long cuadernilloId;
}

