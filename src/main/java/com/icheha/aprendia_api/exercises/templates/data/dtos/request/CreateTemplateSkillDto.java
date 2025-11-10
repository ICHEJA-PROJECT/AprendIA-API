package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemplateSkillDto {
    
    @NotNull(message = "Template ID es requerido")
    private Long templateId;
    
    @NotNull(message = "Skill ID es requerido")
    private Long skillId;
    
    @NotNull(message = "Peso es requerido")
    private BigDecimal peso;
}
