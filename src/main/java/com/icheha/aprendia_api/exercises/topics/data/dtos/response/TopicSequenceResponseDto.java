package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicSequenceResponseDto {
    
    private Long id;
    private Long topicId;
    private String topicName;
    private Long sequenceId;
    private String sequenceName;
    private Integer order;
}
