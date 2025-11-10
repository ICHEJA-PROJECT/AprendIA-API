package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una institución")
public class UpdateInstitutionDto {

    @Schema(description = "RFC de la institución (opcional)", example = "ABC123456789", maxLength = 15)
    private String rfc;

    @Schema(description = "RCT de la institución (opcional)", example = "RCT123456789", maxLength = 20)
    private String rct;

    @Schema(description = "Nombre de la institución (opcional)", example = "Instituto de Educación Actualizado")
    private String name;
}

