package com.icheha.aprendia_api.preferences.region.services.impl;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateExerciseRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.ExerciseRegionResponseDto;
import com.icheha.aprendia_api.preferences.region.data.entities.ExerciseRegionEntity;
import com.icheha.aprendia_api.preferences.region.data.repositories.ExerciseRegionRepository;
import com.icheha.aprendia_api.preferences.region.services.IExerciseRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseRegionServiceImpl implements IExerciseRegionService {

    @Autowired
    private ExerciseRegionRepository exerciseRegionRepository;

    @Override
    public ExerciseRegionResponseDto create(CreateExerciseRegionDto dto) {
        if (exerciseRegionRepository.existsByExerciseIdAndRegionId(dto.getExerciseId(), dto.getRegionId())) {
            throw new IllegalArgumentException("Ya existe una relación entre el ejercicio y la región");
        }

        ExerciseRegionEntity entity = new ExerciseRegionEntity();
        entity.setExerciseId(dto.getExerciseId());
        entity.setRegionId(dto.getRegionId());

        ExerciseRegionEntity savedEntity = exerciseRegionRepository.save(entity);

        ExerciseRegionResponseDto response = new ExerciseRegionResponseDto();
        response.setExerciseId(savedEntity.getExerciseId());
        response.setRegionId(savedEntity.getRegionId());
        return response;
    }

    @Override
    public List<ExerciseRegionResponseDto> findByExercise(Long exerciseId) {
        List<ExerciseRegionEntity> entities = exerciseRegionRepository.findByExerciseId(exerciseId);
        return entities.stream()
                .map(entity -> {
                    ExerciseRegionResponseDto dto = new ExerciseRegionResponseDto();
                    dto.setExerciseId(entity.getExerciseId());
                    dto.setRegionId(entity.getRegionId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findByRegionOnlyIds(Long regionId) {
        List<ExerciseRegionEntity> entities = exerciseRegionRepository.findByRegionId(regionId);
        return entities.stream()
                .map(ExerciseRegionEntity::getExerciseId)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExerciseRegionResponseDto> findByRegion(Long regionId) {
        List<ExerciseRegionEntity> entities = exerciseRegionRepository.findByRegionId(regionId);
        return entities.stream()
                .map(entity -> {
                    ExerciseRegionResponseDto dto = new ExerciseRegionResponseDto();
                    dto.setExerciseId(entity.getExerciseId());
                    dto.setRegionId(entity.getRegionId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

