package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITopicSequenceService {
    
    TopicSequenceResponseDto createTopicSequence(CreateTopicSequenceDto createTopicSequenceDto);
    
    List<TopicSequenceResponseDto> getAllTopicSequences();
    
    Optional<TopicSequenceResponseDto> findById(Long topicId, Long nextTopicId);
    
    TopicSequenceResponseDto update(Long topicId, Long nextTopicId, UpdateTopicSequenceDto updateTopicSequenceDto);
    
    void delete(Long topicId, Long nextTopicId);
}
