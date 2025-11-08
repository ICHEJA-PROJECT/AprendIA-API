package com.icheha.aprendia_api.preferences.impairments.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una nueva discapacidad")
public class CreateImpairmentDto {
    
    @NotBlank(message = "El nombre de la discapacidad es obligatorio")
    @Size(max = 32, message = "El nombre de la discapacidad no puede exceder 32 caracteres")
    @Schema(description = "Nombre de la discapacidad", example = "Discapacidad Visual", maxLength = 32)
    private String name;
}

