package com.icheha.aprendia_api.preferences.words.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una nueva palabra")
public class CreateWordDto {
    
    @NotBlank(message = "La palabra es obligatoria")
    @Schema(description = "La palabra", example = "casa", maxLength = 32)
    private String word;
}

