package com.icheha.aprendia_api.users.person.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTownDto {
    
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    
    @NotNull(message = "ID de municipio es requerido")
    private Long municipalityId;
}

