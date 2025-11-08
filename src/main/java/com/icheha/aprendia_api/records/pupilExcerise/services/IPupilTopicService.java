package com.icheha.aprendia_api.records.pupilExcerise.services;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilTopicDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilTopicResponseDto;

import java.util.List;

public interface IPupilTopicService {
    
    PupilTopicResponseDto createPupilTopic(CreatePupilTopicDto createPupilTopicDto);
    
    PupilTopicResponseDto getPupilTopicById(Integer id);
    
    List<PupilTopicResponseDto> findByPupil(Integer pupilId);
}
