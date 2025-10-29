package com.icheha.aprendia_api.exercises.templates.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponseDto {
    
    private Long id;
    private String title;
    private String instructions;
    private String suggestTime;
    private Map<String, Object> attributes;
    private Long layoutId;
    private String layoutName;
    private Long topicId;
    private String topicName;
}
