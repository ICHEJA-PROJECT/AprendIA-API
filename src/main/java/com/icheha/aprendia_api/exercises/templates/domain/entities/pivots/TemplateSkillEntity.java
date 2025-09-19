package com.icheha.aprendia_api.exercises.templates.domain.entities.pivots;

import com.icheha.aprendia_api.exercises.templates.domain.entities.SkillEntity;
import com.icheha.aprendia_api.exercises.templates.domain.entities.TemplateEntity;
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
    private Long idReactivo;

    @Id
    @Column(name = "id_habilidad", nullable = false)
    private Long idHabilidad;

    @Column(name = "porcentaje", nullable = false)
    private Float porcentaje;

    @Column(name = "bandera", nullable = false)
    private Boolean bandera;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private TemplateEntity reactivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_habilidad", insertable = false, updatable = false)
    private SkillEntity habilidad;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateSkillEntityId implements Serializable {
        private Long idReactivo;
        private Long idHabilidad;
    }
}
