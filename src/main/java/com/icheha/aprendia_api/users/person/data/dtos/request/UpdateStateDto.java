package com.icheha.aprendia_api.users.person.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStateDto {
    
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
}

