package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateSkillRepository extends JpaRepository<TemplateSkillEntity, TemplateSkillEntity.TemplateSkillEntityId> {
    
    @Query("SELECT ts FROM TemplateSkillEntity ts JOIN FETCH ts.template JOIN FETCH ts.skill")
    List<TemplateSkillEntity> findAllWithRelations();
    
    @Query("SELECT ts FROM TemplateSkillEntity ts JOIN FETCH ts.template JOIN FETCH ts.skill WHERE ts.templateId = :templateId")
    List<TemplateSkillEntity> findByTemplateId(@Param("templateId") Long templateId);
    
    @Query("SELECT ts FROM TemplateSkillEntity ts JOIN FETCH ts.template JOIN FETCH ts.skill WHERE ts.skillId = :skillId")
    List<TemplateSkillEntity> findBySkillId(@Param("skillId") Long skillId);
    
    @Query("SELECT ts FROM TemplateSkillEntity ts JOIN FETCH ts.template JOIN FETCH ts.skill WHERE ts.templateId = :templateId AND ts.skillId = :skillId")
    Optional<TemplateSkillEntity> findByTemplateIdAndSkillId(@Param("templateId") Long templateId, @Param("skillId") Long skillId);
}

