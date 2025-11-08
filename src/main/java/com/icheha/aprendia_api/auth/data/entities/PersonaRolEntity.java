package com.icheha.aprendia_api.auth.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_rol")
    private Long idPersonaRol;

    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "id_rol")
    private Long idRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private RolEntity rol;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.icheha.aprendia_api.users.person.data.entities.PersonaEntity.class)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private com.icheha.aprendia_api.users.person.data.entities.PersonaEntity persona;
}
