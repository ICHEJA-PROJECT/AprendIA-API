package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateLearningPathImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.LearningPathImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.LearningPathImpairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.LearningPathImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.ILearningPathImpairmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LearningPathImpairmentServiceImpl implements ILearningPathImpairmentService {

    @Autowired
    private LearningPathImpairmentRepository learningPathImpairmentRepository;

    @Override
    public LearningPathImpairmentResponseDto create(CreateLearningPathImpairmentDto dto) {
        if (learningPathImpairmentRepository.existsByLearningPathIdAndImpairmentId(dto.getLearningPathId(), dto.getImpairmentId())) {
            throw new IllegalArgumentException("Ya existe una relación entre la ruta de aprendizaje y la discapacidad");
        }

        LearningPathImpairment entity = new LearningPathImpairment();
        entity.setLearningPathId(dto.getLearningPathId());
        entity.setImpairmentId(dto.getImpairmentId());

        LearningPathImpairment savedEntity = learningPathImpairmentRepository.save(entity);

        LearningPathImpairmentResponseDto response = new LearningPathImpairmentResponseDto();
        response.setLearningPathId(savedEntity.getLearningPathId());
        response.setImpairmentId(savedEntity.getImpairmentId());
        return response;
    }

    @Override
    public List<LearningPathImpairmentResponseDto> findAll() {
        List<LearningPathImpairment> entities = learningPathImpairmentRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    LearningPathImpairmentResponseDto dto = new LearningPathImpairmentResponseDto();
                    dto.setLearningPathId(entity.getLearningPathId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<LearningPathImpairmentResponseDto> findByLearningPath(Long learningPathId) {
        List<LearningPathImpairment> entities = learningPathImpairmentRepository.findByLearningPathId(learningPathId);
        return entities.stream()
                .map(entity -> {
                    LearningPathImpairmentResponseDto dto = new LearningPathImpairmentResponseDto();
                    dto.setLearningPathId(entity.getLearningPathId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<LearningPathImpairmentResponseDto> findByImpairment(Long impairmentId) {
        List<LearningPathImpairment> entities = learningPathImpairmentRepository.findByImpairmentId(impairmentId);
        return entities.stream()
                .map(entity -> {
                    LearningPathImpairmentResponseDto dto = new LearningPathImpairmentResponseDto();
                    dto.setLearningPathId(entity.getLearningPathId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

