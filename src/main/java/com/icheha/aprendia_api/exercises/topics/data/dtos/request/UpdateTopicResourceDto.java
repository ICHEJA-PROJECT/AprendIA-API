package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTopicResourceDto {
    private Long topicId;
    private Long resourceId;
}

