package com.icheha.aprendia_api.exercises.layouts.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeLayoutResponseDto {
    
    private Long id;
    private String name;
    private String description;
}
