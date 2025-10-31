package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
    
    @Query("SELECT s FROM SkillEntity s JOIN s.templateSkills ts WHERE ts.template.id IN :templateIds")
    List<SkillEntity> findByTemplateIds(@Param("templateIds") List<Long> templateIds);
}
