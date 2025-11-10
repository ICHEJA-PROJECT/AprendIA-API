package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTemplateSkillDto {
    private Long templateId;
    private Long skillId;
    private BigDecimal peso;
}

