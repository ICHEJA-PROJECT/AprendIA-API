package com.icheha.aprendia_api.users.cell.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear una institución")
public class CreateInstitutionDto {
    
    @NotBlank(message = "El RFC es obligatorio")
    @Size(max = 15, message = "El RFC no puede exceder 15 caracteres")
    @Schema(description = "RFC de la institución", example = "ABC123456789", maxLength = 15)
    private String rfc;
    
    @NotBlank(message = "El RCT es obligatorio")
    @Size(max = 20, message = "El RCT no puede exceder 20 caracteres")
    @Schema(description = "RCT de la institución", example = "RCT123456789", maxLength = 20)
    private String rct;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre de la institución", example = "Instituto de Educación")
    private String name;
}

