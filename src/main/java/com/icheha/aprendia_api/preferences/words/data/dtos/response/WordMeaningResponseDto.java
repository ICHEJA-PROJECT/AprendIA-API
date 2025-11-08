package com.icheha.aprendia_api.preferences.words.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de significado de palabra")
public class WordMeaningResponseDto {
    @Schema(description = "ID del significado", example = "1")
    private Long meaningId;
    
    @Schema(description = "ID de la palabra", example = "1")
    private Long wordId;
    
    @Schema(description = "Significado de la palabra", example = "Ejemplo de significado")
    private String meaning;
}

