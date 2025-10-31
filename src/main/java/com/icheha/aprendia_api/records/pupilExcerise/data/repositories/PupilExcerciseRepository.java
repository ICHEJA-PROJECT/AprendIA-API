package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilExcerciseRepository extends JpaRepository<PupilExerciseEntity, Long> {
    
    @Query("SELECT pe FROM PupilExerciseEntity pe WHERE pe.pupilId = :pupilId")
    List<PupilExerciseEntity> findByPupilId(@Param("pupilId") Long pupilId);
    
    @Query("SELECT pe.exerciseId FROM PupilExerciseEntity pe WHERE pe.pupilId = :pupilId")
    List<Long> findExerciseIdsByPupilId(@Param("pupilId") Long pupilId);
    
    @Query("SELECT pe FROM PupilExerciseEntity pe WHERE pe.exerciseId = :exerciseId")
    List<PupilExerciseEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);
    
    @Query("SELECT pe FROM PupilExerciseEntity pe WHERE pe.pupilId = :pupilId AND pe.completedDate IS NULL")
    List<PupilExerciseEntity> findUncompletedByPupilId(@Param("pupilId") Long pupilId);
}
