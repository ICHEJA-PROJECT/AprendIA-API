package com.icheha.aprendia_api.users.student.data.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para crear un progenitor")
public class CreateProgenitorDto {
    
    @NotBlank(message = "El CURP es obligatorio")
    @Schema(description = "CURP del progenitor", example = "ABCD123456EFGH78")
    private String curp;
    
    @NotBlank(message = "El primer nombre es obligatorio")
    @Schema(description = "Primer nombre", example = "Juan")
    private String firstName;
    
    @NotBlank(message = "El segundo nombre es obligatorio")
    @Schema(description = "Segundo nombre", example = "Carlos")
    private String middleName;
    
    @NotBlank(message = "El apellido paterno es obligatorio")
    @Schema(description = "Apellido paterno", example = "García")
    private String paternalSurname;
    
    @NotBlank(message = "El apellido materno es obligatorio")
    @Schema(description = "Apellido materno", example = "López")
    private String maternalSurname;
}

