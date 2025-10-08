package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.exercises.topics.data.entities.LearningPath;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ruta_aprendizaje_discapacidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LearningPathImpairment.LearningPathImpairmentId.class)
public class LearningPathImpairment {
    
    @Id
    @Column(name = "id_ruta_aprendizaje", nullable = false)
    private Long learningPathId;
    
    @Id
    @Column(name = "id_discapacidad", nullable = false)
    private Long impairmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta_aprendizaje", insertable = false, updatable = false)
    private LearningPath learningPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", insertable = false, updatable = false)
    private Impairment impairment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LearningPathImpairmentId implements java.io.Serializable {
        private Long learningPathId;
        private Long impairmentId;
    }
}
