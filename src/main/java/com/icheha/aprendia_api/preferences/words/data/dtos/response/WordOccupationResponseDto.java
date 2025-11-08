package com.icheha.aprendia_api.preferences.words.data.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de relación palabra-ocupación")
public class WordOccupationResponseDto {
    @Schema(description = "ID de la palabra", example = "1")
    private Long wordId;

    @Schema(description = "ID de la ocupación", example = "1")
    private Long occupationId;
}

