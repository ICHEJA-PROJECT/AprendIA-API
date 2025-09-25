package com.icheha.aprendia_api.preferences.occupation.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para WordOccupation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una relación palabra-ocupación")
public class WordOccupationResponseDto {
    
    @Schema(description = "ID de la palabra", example = "1")
    private Long wordId;
    
    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
    
    @Schema(description = "Nombre de la ocupación", example = "Ingeniero de Software")
    private String occupationName;
}
