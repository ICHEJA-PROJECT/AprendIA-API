package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.EjercicioPuntajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EjercicioPuntajeRepository extends JpaRepository<EjercicioPuntajeEntity, Long> {
    
    @Query("SELECT ep FROM EjercicioPuntajeEntity ep WHERE ep.idEjercicio = :idEjercicio")
    List<EjercicioPuntajeEntity> findByEjercicioId(@Param("idEjercicio") Long idEjercicio);
    
    @Query("SELECT ep FROM EjercicioPuntajeEntity ep WHERE ep.idPersona = :idPersona")
    List<EjercicioPuntajeEntity> findByPersonaId(@Param("idPersona") Long idPersona);
    
    @Query("SELECT ep FROM EjercicioPuntajeEntity ep WHERE ep.idEjercicio = :idEjercicio AND ep.idPersona = :idPersona")
    Optional<EjercicioPuntajeEntity> findByEjercicioAndPersona(
            @Param("idEjercicio") Long idEjercicio,
            @Param("idPersona") Long idPersona);
}

