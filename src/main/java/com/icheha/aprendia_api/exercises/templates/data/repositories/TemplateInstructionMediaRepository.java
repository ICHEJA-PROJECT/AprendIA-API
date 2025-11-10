package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateInstructionMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateInstructionMediaRepository extends JpaRepository<TemplateInstructionMediaEntity, TemplateInstructionMediaEntity.TemplateInstructionMediaEntityId> {
    
    @Query("SELECT tim FROM TemplateInstructionMediaEntity tim JOIN FETCH tim.reactivo JOIN FETCH tim.tipoInstruccionMedia")
    List<TemplateInstructionMediaEntity> findAllWithRelations();
    
    @Query("SELECT tim FROM TemplateInstructionMediaEntity tim JOIN FETCH tim.reactivo JOIN FETCH tim.tipoInstruccionMedia WHERE tim.idReactivo = :templateId")
    List<TemplateInstructionMediaEntity> findByTemplateId(@Param("templateId") Long templateId);
    
    @Query("SELECT tim FROM TemplateInstructionMediaEntity tim JOIN FETCH tim.reactivo JOIN FETCH tim.tipoInstruccionMedia WHERE tim.idTipoMedia = :typeMediaId")
    List<TemplateInstructionMediaEntity> findByTypeMediaId(@Param("typeMediaId") Long typeMediaId);
    
    @Query("SELECT tim FROM TemplateInstructionMediaEntity tim JOIN FETCH tim.reactivo JOIN FETCH tim.tipoInstruccionMedia WHERE tim.idReactivo = :templateId AND tim.idTipoMedia = :typeMediaId")
    Optional<TemplateInstructionMediaEntity> findByTemplateIdAndTypeMediaId(@Param("templateId") Long templateId, @Param("typeMediaId") Long typeMediaId);
}

