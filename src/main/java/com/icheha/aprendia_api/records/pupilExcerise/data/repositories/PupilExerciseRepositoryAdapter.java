package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilExerciseEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.mappers.PupilExerciseMapper;
import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;
import com.icheha.aprendia_api.records.pupilExcerise.domain.repositories.IPupilExerciseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PupilExerciseRepositoryAdapter implements IPupilExerciseRepository {
    
    private final PupilExcerciseRepository pupilExerciseRepository;
    private final PupilExerciseMapper pupilExerciseMapper;
    
    public PupilExerciseRepositoryAdapter(PupilExcerciseRepository pupilExerciseRepository, 
                                        PupilExerciseMapper pupilExerciseMapper) {
        this.pupilExerciseRepository = pupilExerciseRepository;
        this.pupilExerciseMapper = pupilExerciseMapper;
    }
    
    @Override
    public PupilExercise save(PupilExercise pupilExercise) {
        PupilExerciseEntity entity = pupilExerciseMapper.toEntity(pupilExercise);
        PupilExerciseEntity savedEntity = pupilExerciseRepository.save(entity);
        return pupilExerciseMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<PupilExercise> findByPupilId(Long pupilId) {
        return pupilExerciseRepository.findByPupilId(pupilId).stream()
                .map(pupilExerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Long> findExerciseIdsByPupilId(Long pupilId) {
        return pupilExerciseRepository.findExerciseIdsByPupilId(pupilId);
    }
    
    @Override
    public List<PupilExercise> findByExerciseId(Long exerciseId) {
        return pupilExerciseRepository.findByExerciseId(exerciseId).stream()
                .map(pupilExerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PupilExercise> findUncompletedByPupilId(Long pupilId) {
        return pupilExerciseRepository.findUncompletedByPupilId(pupilId).stream()
                .map(pupilExerciseMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<PupilExercise> findById(Long id) {
        return pupilExerciseRepository.findById(id)
                .map(pupilExerciseMapper::toDomain);
    }
}
