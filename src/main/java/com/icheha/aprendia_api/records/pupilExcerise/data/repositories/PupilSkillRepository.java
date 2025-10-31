package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilSkillRepository extends JpaRepository<PupilSkillEntity, Long> {
    
    @Query("SELECT ps FROM PupilSkillEntity ps JOIN PupilExerciseEntity pe ON ps.pupilExerciseId = pe.id WHERE pe.pupilId = :pupilId")
    List<PupilSkillEntity> findByPupilId(@Param("pupilId") Long pupilId);
    
    @Query("SELECT ps FROM PupilSkillEntity ps WHERE ps.pupilExerciseId = :pupilExerciseId")
    List<PupilSkillEntity> findByPupilExerciseId(@Param("pupilExerciseId") Long pupilExerciseId);
}
