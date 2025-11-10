package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;

import java.util.List;

public interface ITopicResourceService {
    
    TopicResourceResponseDto createTopicResource(CreateTopicResourceDto createTopicResourceDto);
    
    List<TopicResourceResponseDto> getAllTopicResources();
}
