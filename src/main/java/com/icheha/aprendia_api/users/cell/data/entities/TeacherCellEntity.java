package com.icheha.aprendia_api.users.cell.data.entities;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "celula_educador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(TeacherCellEntity.TeacherCellId.class)
public class TeacherCellEntity {
    
    @Id
    @Column(name = "id_persona", nullable = false)
    private Long teacherId;
    
    @Id
    @Column(name = "id_celula", nullable = false)
    private Long cellId;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.icheha.aprendia_api.users.person.data.entities.PersonaEntity.class)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private PersonaEntity teacher;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_celula", insertable = false, updatable = false)
    private CellEntity cell;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherCellId implements java.io.Serializable {
        private Long teacherId;
        private Long cellId;
    }
}

