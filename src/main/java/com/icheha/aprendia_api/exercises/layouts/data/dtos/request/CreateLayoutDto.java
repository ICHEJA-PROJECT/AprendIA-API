package com.icheha.aprendia_api.exercises.layouts.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLayoutDto {
    
    @NotBlank(message = "Name es requerido")
    private String name;
    
    @NotBlank(message = "Attributes es requerido")
    private String attributes;
    
    private String description;
    private String urlImage;
    private Boolean isActive;
    
    @NotNull(message = "Type Layout ID es requerido")
    private Long typeLayoutId;
}
