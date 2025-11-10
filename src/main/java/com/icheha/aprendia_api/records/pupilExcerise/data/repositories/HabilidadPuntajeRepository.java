package com.icheha.aprendia_api.records.pupilExcerise.data.repositories;

import com.icheha.aprendia_api.records.pupilExcerise.data.entities.HabilidadPuntajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabilidadPuntajeRepository extends JpaRepository<HabilidadPuntajeEntity, Long> {
    
    @Query("SELECT hp FROM HabilidadPuntajeEntity hp WHERE hp.idEjercicio = :idEjercicio")
    List<HabilidadPuntajeEntity> findByEjercicioId(@Param("idEjercicio") Long idEjercicio);
    
    @Query("SELECT hp FROM HabilidadPuntajeEntity hp WHERE hp.idHabilidad = :idHabilidad")
    List<HabilidadPuntajeEntity> findByHabilidadId(@Param("idHabilidad") Long idHabilidad);
    
    @Query("SELECT hp FROM HabilidadPuntajeEntity hp WHERE hp.idUser = :idUser")
    List<HabilidadPuntajeEntity> findByUserId(@Param("idUser") Long idUser);
    
    @Query("SELECT hp FROM HabilidadPuntajeEntity hp WHERE hp.idEjercicio = :idEjercicio AND hp.idHabilidad = :idHabilidad AND hp.idUser = :idUser")
    Optional<HabilidadPuntajeEntity> findByEjercicioAndHabilidadAndUser(
            @Param("idEjercicio") Long idEjercicio,
            @Param("idHabilidad") Long idHabilidad,
            @Param("idUser") Long idUser);
    
    @Query("SELECT hp FROM HabilidadPuntajeEntity hp WHERE hp.idEjercicio = :idEjercicio AND hp.idUser = :idUser")
    List<HabilidadPuntajeEntity> findByEjercicioAndUser(
            @Param("idEjercicio") Long idEjercicio,
            @Param("idUser") Long idUser);
}

