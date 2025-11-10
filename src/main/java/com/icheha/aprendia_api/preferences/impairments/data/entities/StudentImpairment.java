package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.users.student.data.entities.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "educando_discapacidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StudentImpairment.StudentImpairmentId.class)
public class StudentImpairment {
    
    @Id
    @Column(name = "id_educando", nullable = false)
    private Long studentId;
    
    @Id
    @Column(name = "id_discapacidad", nullable = false)
    private Long impairmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_educando", referencedColumnName = "id_educando", insertable = false, updatable = false)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", insertable = false, updatable = false)
    private Impairment impairment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentImpairmentId implements java.io.Serializable {
        private Long studentId;
        private Long impairmentId;
    }
}
