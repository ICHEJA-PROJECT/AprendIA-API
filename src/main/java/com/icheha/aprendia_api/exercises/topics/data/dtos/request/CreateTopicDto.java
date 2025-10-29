package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicDto {
    
    @NotBlank(message = "Name es requerido")
    private String name;
    
    @NotNull(message = "Unit ID es requerido")
    private Long unitId;
}
