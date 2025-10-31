package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTemplatesByTopicsDto {
    
    @NotEmpty(message = "Topic IDs no puede estar vacío")
    private List<Long> topicIds;
}
