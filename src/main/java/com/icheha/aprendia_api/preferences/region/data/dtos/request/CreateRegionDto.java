package com.icheha.aprendia_api.preferences.region.data.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos para crear una nueva región")
public class CreateRegionDto {
    
    @NotBlank(message = "El nombre de la región es obligatorio")
    @Size(max = 32, message = "El nombre de la región no puede exceder 32 caracteres")
    @Schema(description = "Nombre de la región", example = "Lima", maxLength = 32)
    private String name;
}

