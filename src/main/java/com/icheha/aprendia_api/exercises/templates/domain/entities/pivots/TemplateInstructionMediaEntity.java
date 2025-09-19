package com.icheha.aprendia_api.exercises.templates.domain.entities.pivots;

import com.icheha.aprendia_api.exercises.templates.domain.entities.TemplateEntity;
import com.icheha.aprendia_api.exercises.templates.domain.entities.TypeInstructionMediaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "reactivo_instruccion_media")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TemplateInstructionMediaEntity.TemplateInstructionMediaEntityId.class)
public class TemplateInstructionMediaEntity {
    
    @Id
    @Column(name = "id_reactivo", nullable = false)
    private Long idReactivo;

    @Id
    @Column(name = "id_tipo_media", nullable = false)
    private Long idTipoMedia;

    @Column(name = "ruta_media", length = 254, nullable = false)
    private String rutaMedia;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private TemplateEntity reactivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_media", insertable = false, updatable = false)
    private TypeInstructionMediaEntity tipoInstruccionMedia;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateInstructionMediaEntityId implements Serializable {
        private Long idReactivo;
        private Long idTipoMedia;
    }
}
