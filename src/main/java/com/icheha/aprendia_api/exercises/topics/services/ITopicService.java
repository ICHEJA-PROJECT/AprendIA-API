package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITopicService {
    
    TopicResponseDto createTopic(CreateTopicDto createTopicDto);
    
    List<TopicResponseDto> getAllTopics();
    
    Optional<TopicResponseDto> findById(Long id);
    
    TopicResponseDto update(Long id, UpdateTopicDto updateTopicDto);
    
    void delete(Long id);
    
    List<TopicResponseDto> getTopicsByPupilLearningPath(Integer id, Integer learningPathId);
    
    List<TopicResponseDto> getTopicsByPupil(Integer id);
    
    List<TopicResponseDto> getTopicsByUnit(Long unitId);
    
    List<LearningPathResponseDto> getLearningPathsByTopicId(Integer id);
    
    List<LearningPathResponseDto> getLearningPathByTopic(Integer id);
}
