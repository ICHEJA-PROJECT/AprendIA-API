package com.icheha.aprendia_api.exercises.templates.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.pivots.TemplateInstructionMediaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_instruccion_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeInstructionMediaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_media")
    private Long idTipoMedia;

    @Column(name = "nombre", length = 32, nullable = false)
    private String nombre;

    // Relaciones
    @OneToMany(mappedBy = "tipoInstruccionMedia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemplateInstructionMediaEntity> templateInstructionMedias;
}
