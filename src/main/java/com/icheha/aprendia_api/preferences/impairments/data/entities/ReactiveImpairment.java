package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.exercises.templates.data.entities.TemplateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reactivo_discapacidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReactiveImpairment.ReactiveImpairmentId.class)
public class ReactiveImpairment {
    
    @Id
    @Column(name = "id_reactivo", nullable = false)
    private Long reactiveId;
    
    @Id
    @Column(name = "id_discapacidad", nullable = false)
    private Long impairmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", insertable = false, updatable = false)
    private TemplateEntity reactive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", insertable = false, updatable = false)
    private Impairment impairment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReactiveImpairmentId implements java.io.Serializable {
        private Long reactiveId;
        private Long impairmentId;
    }
}
