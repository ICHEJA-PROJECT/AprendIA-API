package com.icheha.aprendia_api.users.cell.data.entities;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "celula")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CellEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_celula")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_institucion", nullable = false)
    private InstitutionEntity institution;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.icheha.aprendia_api.users.person.data.entities.PersonaEntity.class)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity coordinator;
    
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime startDate;
    
    @Column(name = "fecha_final", nullable = false)
    private LocalDateTime endDate;
    
    @OneToMany(mappedBy = "cell", fetch = FetchType.LAZY)
    private List<TeacherCellEntity> teachers;
}

