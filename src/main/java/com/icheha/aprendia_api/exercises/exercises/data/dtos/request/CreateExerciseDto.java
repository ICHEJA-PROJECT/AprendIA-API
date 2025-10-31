package com.icheha.aprendia_api.exercises.exercises.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseDto {
    private String context;
    private Long templateId;
    
    public String getContext() {
        return context;
    }
    
    public Long getTemplateId() {
        return templateId;
    }
}
