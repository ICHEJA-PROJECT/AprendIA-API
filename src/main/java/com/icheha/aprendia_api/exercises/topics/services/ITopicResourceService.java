package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITopicResourceService {
    
    TopicResourceResponseDto createTopicResource(CreateTopicResourceDto createTopicResourceDto);
    
    List<TopicResourceResponseDto> getAllTopicResources();
    
    Optional<TopicResourceResponseDto> findById(Long topicId, Long resourceId);
    
    TopicResourceResponseDto update(Long topicId, Long resourceId, UpdateTopicResourceDto updateTopicResourceDto);
    
    void delete(Long topicId, Long resourceId);
}
