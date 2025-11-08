package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilTopicDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilTopicResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilTopicEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.repositories.PupilTopicRepository;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PupilTopicServiceImpl implements IPupilTopicService {
    
    @Autowired
    private PupilTopicRepository pupilTopicRepository;
    
    @Override
    public PupilTopicResponseDto createPupilTopic(CreatePupilTopicDto createPupilTopicDto) {
        PupilTopicEntity entity = new PupilTopicEntity();
        entity.setPupilId(createPupilTopicDto.getPupilId());
        entity.setTopicId(createPupilTopicDto.getTopicId());
        
        PupilTopicEntity savedEntity = pupilTopicRepository.save(entity);
        
        PupilTopicResponseDto response = new PupilTopicResponseDto();
        response.setId(savedEntity.getId());
        response.setPupilId(savedEntity.getPupilId());
        response.setTopicId(savedEntity.getTopicId());
        return response;
    }
    
    @Override
    public PupilTopicResponseDto getPupilTopicById(Integer id) {
        PupilTopicEntity entity = pupilTopicRepository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("PupilTopic no encontrado con ID: " + id));
        
        PupilTopicResponseDto response = new PupilTopicResponseDto();
        response.setId(entity.getId());
        response.setPupilId(entity.getPupilId());
        response.setTopicId(entity.getTopicId());
        return response;
    }
    
    @Override
    public List<PupilTopicResponseDto> findByPupil(Integer pupilId) {
        List<PupilTopicEntity> entities = pupilTopicRepository.findByPupilId(pupilId.longValue());
        return entities.stream()
                .map(entity -> {
                    PupilTopicResponseDto response = new PupilTopicResponseDto();
                    response.setId(entity.getId());
                    response.setPupilId(entity.getPupilId());
                    response.setTopicId(entity.getTopicId());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
