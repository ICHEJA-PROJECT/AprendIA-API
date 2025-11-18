package com.icheha.aprendia_api.users.student.data.entities;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pariente", 
       uniqueConstraints = @UniqueConstraint(
           columnNames = {"persona_id", "pariente_id", "rol_pariente_id"}
       ))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pariente")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private PersonaEntity persona; // El estudiante
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pariente_id", nullable = false)
    private PersonaEntity pariente; // El pariente (padre, madre, tutor, etc.)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_pariente_id", nullable = false)
    private RolParienteEntity rolPariente; // Tipo de relación
}

