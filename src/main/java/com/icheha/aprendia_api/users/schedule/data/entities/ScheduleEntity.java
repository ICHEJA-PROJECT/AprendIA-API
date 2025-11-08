package com.icheha.aprendia_api.users.schedule.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "horarios_disponibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horarios_disponibles")
    private Long id;
    
    @Column(name = "dia", nullable = false, length = 20)
    private String day;
    
    @Column(name = "hour", nullable = false)
    private String hour;
    
    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<SchedulePersonEntity> schedulePeople;
}

