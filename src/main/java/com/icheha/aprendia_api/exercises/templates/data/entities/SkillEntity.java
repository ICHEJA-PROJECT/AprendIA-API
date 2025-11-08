package com.icheha.aprendia_api.exercises.templates.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateSkillEntity;
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
    @Column(name = "id_agenda")
    private Long idHabilidad;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    
    @Column(name = "url", length = 500)
    private String url;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 50)
    private TipoHabilidadEnum tipo;
    
    @Column(name = "id_categoria")
    private Long idCategoria;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private CategoriaHabilidadEntity categoria;

    // Relaciones
    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateSkillEntity> templateSkills;
    
    public enum TipoHabilidadEnum {
        COGNITIVA, MOTORA, SOCIAL, LINGUISTICA, OTRA
    }
}
