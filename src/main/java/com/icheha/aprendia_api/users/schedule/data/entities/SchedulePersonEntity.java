package com.icheha.aprendia_api.users.schedule.data.entities;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal_horarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SchedulePersonEntity.SchedulePersonId.class)
public class SchedulePersonEntity {
    
    @Id
    @Column(name = "id_usuario_rol", nullable = false)
    private Long rolePersonId;
    
    @Id
    @Column(name = "id_horario_disponible", nullable = false)
    private Long scheduleId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_rol", insertable = false, updatable = false)
    private UserRolEntity rolePerson;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horario_disponible", insertable = false, updatable = false)
    private ScheduleEntity schedule;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SchedulePersonId implements java.io.Serializable {
        private Long rolePersonId;
        private Long scheduleId;
    }
}

