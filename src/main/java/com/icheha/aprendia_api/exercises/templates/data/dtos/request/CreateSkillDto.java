package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSkillDto {
    
    @NotBlank(message = "Name es requerido")
    private String name;
}
