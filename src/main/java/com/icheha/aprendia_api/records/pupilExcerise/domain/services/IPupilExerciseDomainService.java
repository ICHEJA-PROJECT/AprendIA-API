package com.icheha.aprendia_api.records.pupilExcerise.domain.services;

import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;

import java.util.List;
import java.util.Optional;

public interface IPupilExerciseDomainService {
    
    PupilExercise createPupilExercise(PupilExercise pupilExercise);
    
    List<PupilExercise> findByPupilId(Long pupilId);
    
    List<Long> findExerciseIdsByPupilId(Long pupilId);
    
    List<PupilExercise> findByExerciseId(Long exerciseId);
    
    List<PupilExercise> findUncompletedByPupilId(Long pupilId);
    
    PupilExercise updatePupilExercise(Long id, PupilExercise pupilExercise);
    
    Optional<PupilExercise> findById(Long id);
}
