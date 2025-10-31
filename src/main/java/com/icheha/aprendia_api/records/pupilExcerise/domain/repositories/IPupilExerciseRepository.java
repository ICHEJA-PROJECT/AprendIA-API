package com.icheha.aprendia_api.records.pupilExcerise.domain.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.domain.entities.PupilExercise;

import java.util.List;
import java.util.Optional;

public interface IPupilExerciseRepository {
    
    PupilExercise save(PupilExercise pupilExercise);
    
    List<PupilExercise> findByPupilId(Long pupilId);
    
    List<Long> findExerciseIdsByPupilId(Long pupilId);
    
    List<PupilExercise> findByExerciseId(Long exerciseId);
    
    List<PupilExercise> findUncompletedByPupilId(Long pupilId);
    
    Optional<PupilExercise> findById(Long id);
}
