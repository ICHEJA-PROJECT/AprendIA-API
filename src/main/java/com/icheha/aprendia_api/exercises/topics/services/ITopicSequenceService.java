package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;

import java.util.List;

public interface ITopicSequenceService {
    
    TopicSequenceResponseDto createTopicSequence(CreateTopicSequenceDto createTopicSequenceDto);
    
    List<TopicSequenceResponseDto> getAllTopicSequences();
}
