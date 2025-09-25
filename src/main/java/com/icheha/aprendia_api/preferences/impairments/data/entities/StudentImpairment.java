package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.auth.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiante_discapacidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentImpairment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante_discapacidad")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private PersonaEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", nullable = false)
    private Impairment impairment;

    @Column(name = "descripcion", length = 500)
    private String descripcion;
}
