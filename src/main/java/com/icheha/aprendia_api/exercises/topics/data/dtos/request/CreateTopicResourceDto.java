package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicResourceDto {
    
    @NotNull(message = "Topic ID es requerido")
    private Long topicId;
    
    @NotNull(message = "Resource ID es requerido")
    private Long resourceId;
}
