package com.icheha.aprendia_api.preferences.impairments.data.entities;

import com.icheha.aprendia_api.exercises.topics.data.entities.ResourceEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recurso_discapacidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ResourceImpairment.ResourceImpairmentId.class)
public class ResourceImpairment {
    
    @Id
    @Column(name = "id_recurso", nullable = false)
    private Long resourceId;
    
    @Id
    @Column(name = "id_discapacidad", nullable = false)
    private Long impairmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso", insertable = false, updatable = false)
    private ResourceEntity resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discapacidad", insertable = false, updatable = false)
    private Impairment impairment;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResourceImpairmentId implements java.io.Serializable {
        private Long resourceId;
        private Long impairmentId;
    }
}
