package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.MetodologiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetodologiaRepository extends JpaRepository<MetodologiaEntity, Long> {
    
    @Query("SELECT m FROM MetodologiaEntity m LEFT JOIN FETCH m.rutasAprendizaje WHERE m.idMetodologia = :id")
    Optional<MetodologiaEntity> findByIdWithRelations(@Param("id") Long id);
    
    @Query("SELECT m FROM MetodologiaEntity m LEFT JOIN FETCH m.rutasAprendizaje")
    List<MetodologiaEntity> findAllWithRelations();
    
    @Query("SELECT m FROM MetodologiaEntity m LEFT JOIN FETCH m.rutasAprendizaje WHERE m.nombre LIKE CONCAT('%', :nombre, '%')")
    List<MetodologiaEntity> findByNombreContaining(@Param("nombre") String nombre);
}

