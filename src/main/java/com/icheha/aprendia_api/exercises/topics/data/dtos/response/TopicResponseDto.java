package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponseDto {
    
    private Long id;
    private String name;
    private String description;
    private Long unitId;
    private String unitName;
    private String urlImagen;
}
