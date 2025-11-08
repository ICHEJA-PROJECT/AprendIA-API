package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "persona_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaPacienteEntity {
    
    @Id
    @Column(name = "id_persona")
    private Long idPersona;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    @MapsId
    private PersonaEntity persona;
    
    @Column(name = "id_persona_paciente", nullable = false)
    private Long idPersonaPaciente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona_paciente", insertable = false, updatable = false)
    private PersonaEntity personaPaciente;
    
    @Column(name = "rel_paciente", nullable = false)
    private Long relPacienteId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rel_paciente", insertable = false, updatable = false)
    private RelPacienteEntity relPaciente;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}

