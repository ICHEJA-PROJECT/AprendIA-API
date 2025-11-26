package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUnitDto {
    
    @NotBlank(message = "Name es requerido")
    private String name;
    
    @NotBlank(message = "Description es requerido")
    private String description;
    
    private String urlImagen;
    
    @NotNull(message = "Cuadernillo ID es requerido")
    private Long cuadernilloId;
}
