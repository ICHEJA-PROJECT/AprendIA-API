package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilTopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilTopicRepository extends JpaRepository<PupilTopicEntity, Long> {
    
    @Query("SELECT pt FROM PupilTopicEntity pt WHERE pt.pupilId = :pupilId")
    List<PupilTopicEntity> findByPupilId(@Param("pupilId") Long pupilId);
}

