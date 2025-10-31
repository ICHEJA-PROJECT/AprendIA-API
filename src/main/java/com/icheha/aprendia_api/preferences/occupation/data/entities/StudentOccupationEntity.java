package com.icheha.aprendia_api.preferences.occupation.data.entities;

import com.icheha.aprendia_api.preferences.occupation.domain.entities.StudentOccupationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad JPA para StudentOccupation
 * Mapea a la tabla 'estudiante_ocupacion'
 */
@Entity
@Table(name = "estudiante_ocupacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StudentOccupationId.class)
public class StudentOccupationEntity {
    
    @Id
    @Column(name = "id_estudiante", nullable = false)
    private Long studentId;

    @Id
    @Column(name = "id_ocupacion", nullable = false)
    private Long occupationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ocupacion", insertable = false, updatable = false)
    private OccupationEntity occupation;
}

