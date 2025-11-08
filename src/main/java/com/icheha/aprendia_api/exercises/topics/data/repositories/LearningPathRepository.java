package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.LearningPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {
    
    @Query("SELECT lp FROM LearningPath lp JOIN FETCH lp.perfil WHERE lp.idRuta = :id")
    Optional<LearningPath> findByIdWithPerfil(@Param("id") Long id);
    
    @Query("SELECT lp FROM LearningPath lp JOIN FETCH lp.metodologia WHERE lp.idRuta = :id")
    Optional<LearningPath> findByIdWithMetodologia(@Param("id") Long id);
    
    List<LearningPath> findByIdPerfil(Long idPerfil);
    
    List<LearningPath> findByIdMetodologia(Long idMetodologia);
    
    List<LearningPath> findByNombreContainingIgnoreCase(String nombre);
}

