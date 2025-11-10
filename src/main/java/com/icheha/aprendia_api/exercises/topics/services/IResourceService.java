package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateResourceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.ResourceResponseDto;

import java.util.List;

public interface IResourceService {
    
    ResourceResponseDto createResource(CreateResourceDto createResourceDto);
    
    List<ResourceResponseDto> getAllResources();
    
    ResourceResponseDto getResourceById(Long id);
    
    ResourceResponseDto update(Long id, UpdateResourceDto updateResourceDto);
    
    void delete(Long id);
    
    List<ResourceResponseDto> getResourcesByPupilLearningPath(Integer id, Integer learningPathId);
    
    List<ResourceResponseDto> getResourcesByTopicLearningPath(Integer id, Integer learningPathId);
    
    List<ResourceResponseDto> getResourcesByPupil(Integer id);
    
    List<ResourceResponseDto> getResourcesByTopic(Integer id);
}
