package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recurso_discapacidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceImpairment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso_discapacidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso", nullable = false)
    private ResourceEntity resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", nullable = false)
    private Impairment impairment;
}
