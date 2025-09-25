package com.icheha.aprendia_api.preferences.occupation.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para crear una relación palabra-ocupación
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una relación palabra-ocupación")
public class CreateWordOccupationDto {
    
    @NotNull(message = "El ID de la palabra es obligatorio")
    @Schema(description = "ID de la palabra", example = "1")
    private Long wordId;
    
    @NotNull(message = "El ID de la ocupación es obligatorio")
    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
}
