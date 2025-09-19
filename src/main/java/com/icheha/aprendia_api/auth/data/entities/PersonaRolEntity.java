package com.icheha.aprendia_api.auth.data.entities;

import com.icheha.aprendia_api.auth.domain.interfaces.PersonaRolI;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaI;
import com.icheha.aprendia_api.auth.domain.interfaces.RolI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad TypeORM para PersonaRol
 * Implementa PersonaRolI y mapea a la tabla 'persona_rol'
 * Tabla de relación Many-to-Many con eager loading
 */
@Entity
@Table(name = "persona_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRolEntity implements PersonaRolI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona_rol")
    private Long idPersonaRol;

    @Column(name = "id_persona", nullable = false)
    private Long idPersona;

    @Column(name = "id_rol", nullable = false)
    private Long idRol;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private RolEntity rol;

    // Implementación de la interfaz PersonaRolI
    @Override
    public PersonaI getPersona() {
        return persona;
    }

    @Override
    public void setPersona(PersonaI persona) {
        this.persona = (PersonaEntity) persona;
    }

    @Override
    public RolI getRol() {
        return rol;
    }

    @Override
    public void setRol(RolI rol) {
        this.rol = (RolEntity) rol;
    }
}
