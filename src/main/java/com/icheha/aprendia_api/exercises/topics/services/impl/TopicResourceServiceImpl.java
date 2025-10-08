package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResourceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicResourceServiceImpl implements ITopicResourceService {
    
    @Override
    public TopicResourceResponseDto createTopicResource(CreateTopicResourceDto createTopicResourceDto) {
        TopicResourceResponseDto topicResource = new TopicResourceResponseDto();
        topicResource.setId(1L);
        topicResource.setTopicId(createTopicResourceDto.getTopicId());
        topicResource.setResourceId(createTopicResourceDto.getResourceId());
        return topicResource;
    }
    
    @Override
    public List<TopicResourceResponseDto> getAllTopicResources() {
        List<TopicResourceResponseDto> topicResources = new ArrayList<>();
        
        TopicResourceResponseDto topicResource1 = new TopicResourceResponseDto();
        topicResource1.setId(1L);
        topicResource1.setTopicId(1L);
        topicResource1.setResourceId(1L);
        topicResources.add(topicResource1);
        
        return topicResources;
    }
}
