package com.icheha.aprendia_api.exercises.templates.data.entities.pivots;

import com.icheha.aprendia_api.exercises.templates.data.entities.SkillEntity;
import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "reactivo_habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TemplateSkillEntity.TemplateSkillEntityId.class)
public class TemplateSkillEntity {
    
    @Id
    @Column(name = "id_reactivo", nullable = false)
    private Long templateId;

    @Id
    @Column(name = "id_habilidad", nullable = false)
    private Long skillId;

    @Column(name = "porcentaje", nullable = false)
    private Float porcentage;

    @Column(name = "bandera", nullable = false)
    private Boolean flag;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private TemplateEntity template;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidad", insertable = false, updatable = false)
    private SkillEntity skill;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateSkillEntityId implements Serializable {
        private Long templateId;
        private Long skillId;
    }
}
