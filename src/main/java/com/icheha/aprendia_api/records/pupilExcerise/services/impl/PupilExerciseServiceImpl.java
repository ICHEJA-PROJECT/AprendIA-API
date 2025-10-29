package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilExerciseResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.mappers.PupilExerciseMapper;
import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;
import com.icheha.aprendia_api.records.pupilExcerise.domain.services.IPupilExerciseDomainService;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PupilExerciseServiceImpl implements IPupilExerciseService {
    
    private final IPupilExerciseDomainService pupilExerciseDomainService;
    private final PupilExerciseMapper pupilExerciseMapper;
    
    public PupilExerciseServiceImpl(IPupilExerciseDomainService pupilExerciseDomainService, 
                                  PupilExerciseMapper pupilExerciseMapper) {
        this.pupilExerciseDomainService = pupilExerciseDomainService;
        this.pupilExerciseMapper = pupilExerciseMapper;
    }
    
    @Override
    public PupilExerciseResponseDto createPupilExercise(CreatePupilExerciseDto createPupilExerciseDto) {
        PupilExercise pupilExercise = pupilExerciseMapper.toDomain(createPupilExerciseDto);
        PupilExercise savedPupilExercise = pupilExerciseDomainService.createPupilExercise(pupilExercise);
        return pupilExerciseMapper.toResponseDto(savedPupilExercise);
    }
    
    @Override
    public List<Integer> getExerciseIdsByPupil(Integer id) {
        List<Long> exerciseIds = pupilExerciseDomainService.findExerciseIdsByPupilId(Long.valueOf(id));
        return exerciseIds.stream()
                .map(Long::intValue)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ExerciseResponseDto> getAssignedExercisesByPupil(Integer id) {
        // TODO: Implement logic to get assigned exercises
        // This would require integration with exercise service
        return List.of();
    }
    
    @Override
    public List<PupilExerciseResponseDto> getExercisesByPupil(Integer id) {
        List<PupilExercise> pupilExercises = pupilExerciseDomainService.findByPupilId(Long.valueOf(id));
        return pupilExerciseMapper.toResponseDtoList(pupilExercises);
    }
    
    @Override
    public List<PupilExerciseResponseDto> getExercisesByExerciseId(Integer id) {
        List<PupilExercise> pupilExercises = pupilExerciseDomainService.findByExerciseId(Long.valueOf(id));
        return pupilExerciseMapper.toResponseDtoList(pupilExercises);
    }
    
    @Override
    public PupilExerciseResponseDto updatePupilExercise(Integer id, UpdatePupilExerciseDto updatePupilExerciseDto) {
        // TODO: Implement update logic
        // This would require finding the existing pupil exercise and updating it
        return null;
    }
}
