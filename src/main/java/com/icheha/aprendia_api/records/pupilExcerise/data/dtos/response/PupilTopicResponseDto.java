package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilTopicResponseDto {
    
    private Long id;
    private Long pupilId;
    private String pupilName;
    private Long topicId;
    private String topicName;
    private Boolean completed;
    private Double progress;
}
