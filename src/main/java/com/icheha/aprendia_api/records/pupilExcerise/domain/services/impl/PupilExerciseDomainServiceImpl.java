package com.icheha.aprendia_api.records.pupilExcerise.domain.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;
import com.icheha.aprendia_api.records.pupilExcerise.domain.repositories.IPupilExerciseRepository;
import com.icheha.aprendia_api.records.pupilExcerise.domain.services.IPupilExerciseDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PupilExerciseDomainServiceImpl implements IPupilExerciseDomainService {
    
    private final IPupilExerciseRepository pupilExerciseRepository;
    
    public PupilExerciseDomainServiceImpl(IPupilExerciseRepository pupilExerciseRepository) {
        this.pupilExerciseRepository = pupilExerciseRepository;
    }
    
    @Override
    public PupilExercise createPupilExercise(PupilExercise pupilExercise) {
        return pupilExerciseRepository.save(pupilExercise);
    }
    
    @Override
    public List<PupilExercise> findByPupilId(Long pupilId) {
        return pupilExerciseRepository.findByPupilId(pupilId);
    }
    
    @Override
    public List<Long> findExerciseIdsByPupilId(Long pupilId) {
        return pupilExerciseRepository.findExerciseIdsByPupilId(pupilId);
    }
    
    @Override
    public List<PupilExercise> findByExerciseId(Long exerciseId) {
        return pupilExerciseRepository.findByExerciseId(exerciseId);
    }
    
    @Override
    public List<PupilExercise> findUncompletedByPupilId(Long pupilId) {
        return pupilExerciseRepository.findUncompletedByPupilId(pupilId);
    }
    
    @Override
    public PupilExercise updatePupilExercise(Long id, PupilExercise pupilExercise) {
        return pupilExerciseRepository.save(pupilExercise);
    }
    
    @Override
    public Optional<PupilExercise> findById(Long id) {
        return pupilExerciseRepository.findById(id);
    }
}
