package com.icheha.aprendia_api.exercises.exercises.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponseDto {
    
    private Long id;
    private Map<String, Object> context;
    private Long templateId;
    private String name;
    private String description;
    private String templateNombre;
    private String templateDescripcion;
    private String templateInstrucciones;
}
