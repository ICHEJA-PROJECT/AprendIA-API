package com.icheha.aprendia_api.exercises.templates.domain.entities;

import com.icheha.aprendia_api.exercises.templates.domain.entities.pivots.TemplateSkillEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "habilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad")
    private Long idHabilidad;

    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;

    // Relaciones
    @OneToMany(mappedBy = "habilidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateSkillEntity> templateSkills;
}
