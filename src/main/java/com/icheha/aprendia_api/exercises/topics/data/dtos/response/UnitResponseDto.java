package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitResponseDto {
    
    private Long id;
    private String name;
    private String description;
    private Long cuadernilloId;
    private String cuadernilloNombre;
    private String urlImagen;
}
