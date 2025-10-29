package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateTopicSequenceDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.TopicSequenceResponseDto;
import com.icheha.aprendia_api.exercises.topics.services.ITopicSequenceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicSequenceServiceImpl implements ITopicSequenceService {
    
    @Override
    public TopicSequenceResponseDto createTopicSequence(CreateTopicSequenceDto createTopicSequenceDto) {
        TopicSequenceResponseDto topicSequence = new TopicSequenceResponseDto();
        topicSequence.setId(1L);
        topicSequence.setTopicId(createTopicSequenceDto.getTopicId());
        topicSequence.setSequenceId(createTopicSequenceDto.getSequenceId());
        topicSequence.setOrder(createTopicSequenceDto.getOrder());
        return topicSequence;
    }
    
    @Override
    public List<TopicSequenceResponseDto> getAllTopicSequences() {
        List<TopicSequenceResponseDto> topicSequences = new ArrayList<>();
        
        TopicSequenceResponseDto topicSequence1 = new TopicSequenceResponseDto();
        topicSequence1.setId(1L);
        topicSequence1.setTopicId(1L);
        topicSequence1.setSequenceId(1L);
        topicSequence1.setOrder(1);
        topicSequences.add(topicSequence1);
        
        return topicSequences;
    }
}
