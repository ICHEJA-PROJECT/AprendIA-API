package com.icheha.aprendia_api.exercises.templates.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateSkillResponseDto {
    
    private Long id;
    private Long templateId;
    private String templateName;
    private Long skillId;
    private String skillName;
}
