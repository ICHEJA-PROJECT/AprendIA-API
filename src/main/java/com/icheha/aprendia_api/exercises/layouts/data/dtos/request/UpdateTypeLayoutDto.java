package com.icheha.aprendia_api.exercises.layouts.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar un tipo de layout")
public class UpdateTypeLayoutDto {

    @Schema(description = "Nombre del tipo de layout (opcional)", example = "Tipo Layout Actualizado")
    private String name;
}

