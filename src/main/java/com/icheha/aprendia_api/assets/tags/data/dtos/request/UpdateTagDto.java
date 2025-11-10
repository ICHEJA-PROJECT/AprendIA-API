package com.icheha.aprendia_api.assets.tags.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un tag")
public class UpdateTagDto {

    @Schema(description = "Nombre del tag (opcional)", example = "Tag Actualizado")
    private String name;
}

