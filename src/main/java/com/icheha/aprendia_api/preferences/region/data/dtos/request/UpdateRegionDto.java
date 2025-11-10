package com.icheha.aprendia_api.preferences.region.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una región")
public class UpdateRegionDto {

    @Schema(description = "Nombre de la región (opcional)", example = "Lima Actualizada", maxLength = 32)
    private String name;
}

