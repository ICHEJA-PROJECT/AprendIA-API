package com.icheha.aprendia_api.preferences.occupation.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una ocupación")
public class UpdateOccupationDto {

    @Schema(description = "Nombre de la ocupación (opcional)", example = "Ingeniero de Software Actualizado", maxLength = 32)
    private String name;
}

