package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemplateDto {
    
    @NotBlank(message = "Title es requerido")
    private String title;
    
    @NotBlank(message = "Instructions es requerido")
    private String instructions;
    
    @NotBlank(message = "SuggestTime es requerido")
    private String suggestTime;
    
    @NotNull(message = "Attributes es requerido")
    private Map<String, Object> attributes;
    
    @NotNull(message = "Layout ID es requerido")
    private Long layout;
    
    @NotNull(message = "Topic ID es requerido")
    private Long topic;
}
