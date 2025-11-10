package com.icheha.aprendia_api.preferences.words.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para actualizar una palabra")
public class UpdateWordDto {

    @Schema(description = "La palabra (opcional)", example = "casa actualizada", maxLength = 32)
    private String word;
}

