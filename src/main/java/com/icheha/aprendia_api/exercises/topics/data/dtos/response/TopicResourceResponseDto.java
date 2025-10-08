package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResourceResponseDto {
    
    private Long id;
    private Long topicId;
    private String topicName;
    private Long resourceId;
    private String resourceName;
}
