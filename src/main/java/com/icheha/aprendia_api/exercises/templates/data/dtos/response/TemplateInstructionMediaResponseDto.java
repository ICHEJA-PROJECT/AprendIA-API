package com.icheha.aprendia_api.exercises.templates.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateInstructionMediaResponseDto {
    
    private Long id;
    private Long templateId;
    private String templateName;
    private Long instructionMediaId;
    private String instructionMediaName;
    private String mediaUrl;
    private Integer order;
}
