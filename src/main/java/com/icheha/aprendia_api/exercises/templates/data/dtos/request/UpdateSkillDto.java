package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una habilidad")
public class UpdateSkillDto {

    @Schema(description = "Nombre de la habilidad (opcional)", example = "Habilidad Actualizada")
    private String name;

    @Schema(description = "URL de la habilidad (opcional)", example = "https://example.com/skill")
    private String url;

    @Schema(description = "Tipo de habilidad (opcional)", example = "COGNITIVA")
    private String tipo;

    @Schema(description = "ID de la categoría (opcional)", example = "1")
    private Long idCategoria;
}

