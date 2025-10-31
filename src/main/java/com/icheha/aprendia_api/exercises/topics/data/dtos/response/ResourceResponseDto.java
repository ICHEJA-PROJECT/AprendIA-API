package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResponseDto {
    
    private Long id;
    private String title;
    private String content;
    private Long layoutId;
    private String layoutName;
    private List<Long> topicIds;
}
