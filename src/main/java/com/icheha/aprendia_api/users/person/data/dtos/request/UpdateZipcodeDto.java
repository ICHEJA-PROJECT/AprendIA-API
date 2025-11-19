package com.icheha.aprendia_api.users.person.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateZipcodeDto {
    
    @NotBlank(message = "Código postal es requerido")
    @Pattern(regexp = "\\d{5}", message = "El código postal debe tener 5 dígitos")
    private String codigo;
}

