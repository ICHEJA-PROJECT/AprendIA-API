package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilTopicDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilTopicResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilTopicService;
import org.springframework.stereotype.Service;

@Service
public class PupilTopicServiceImpl implements IPupilTopicService {
    
    @Override
    public PupilTopicResponseDto createPupilTopic(CreatePupilTopicDto createPupilTopicDto) {
        PupilTopicResponseDto pupilTopic = new PupilTopicResponseDto();
        pupilTopic.setId(1L);
        pupilTopic.setPupilId(createPupilTopicDto.getPupilId());
        pupilTopic.setPupilName("Pupil Mock");
        pupilTopic.setTopicId(createPupilTopicDto.getTopicId());
        pupilTopic.setTopicName("Topic Mock");
        pupilTopic.setCompleted(false);
        pupilTopic.setProgress(0.0);
        return pupilTopic;
    }
    
    @Override
    public PupilTopicResponseDto getPupilTopicById(Integer id) {
        PupilTopicResponseDto pupilTopic = new PupilTopicResponseDto();
        pupilTopic.setId(Long.valueOf(id));
        pupilTopic.setPupilId(1L);
        pupilTopic.setPupilName("Pupil Mock");
        pupilTopic.setTopicId(1L);
        pupilTopic.setTopicName("Topic Mock");
        pupilTopic.setCompleted(true);
        pupilTopic.setProgress(100.0);
        return pupilTopic;
    }
}
