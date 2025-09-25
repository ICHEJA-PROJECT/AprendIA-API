package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.exercises.templates.domain.entities.TemplateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reactivo_discapacidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveImpairment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reactivo_discapacidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reactivo", nullable = false)
    private TemplateEntity reactive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", nullable = false)
    private Impairment impairment;
}
