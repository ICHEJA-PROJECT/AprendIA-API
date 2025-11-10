package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTypeInstructionMediaDto {
    private String name;
    private String description;
}

