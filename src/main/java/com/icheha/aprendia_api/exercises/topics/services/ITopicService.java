package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;

import java.util.List;

public interface ITopicService {
    
    TopicResponseDto createTopic(CreateTopicDto createTopicDto);
    
    List<TopicResponseDto> getAllTopics();
    
    List<TopicResponseDto> getTopicsByPupilLearningPath(Integer id, Integer learningPathId);
    
    List<TopicResponseDto> getTopicsByPupil(Integer id);
    
    List<LearningPathResponseDto> getLearningPathsByTopicId(Integer id);
    
    List<LearningPathResponseDto> getLearningPathByTopic(Integer id);
}
