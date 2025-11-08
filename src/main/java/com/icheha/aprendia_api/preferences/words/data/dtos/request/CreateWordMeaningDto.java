package com.icheha.aprendia_api.preferences.words.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear un significado de palabra")
public class CreateWordMeaningDto {
    @NotNull(message = "El ID de la palabra es obligatorio")
    @Schema(description = "ID de la palabra", example = "1")
    private Long wordId;

    @NotNull(message = "El significado es obligatorio")
    @Schema(description = "Significado de la palabra", example = "Ejemplo de significado")
    private String meaning;
}

