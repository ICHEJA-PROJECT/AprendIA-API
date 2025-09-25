package com.icheha.aprendia_api.exercises.exercises.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseDto {
    
    @NotNull(message = "Context es requerido")
    private Map<String, Object> context;

    @NotNull(message = "Template ID es requerido")
    private Long templateId;
}
