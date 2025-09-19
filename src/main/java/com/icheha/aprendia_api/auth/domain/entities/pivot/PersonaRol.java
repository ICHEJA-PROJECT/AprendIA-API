package com.icheha.aprendia_api.auth.domain.entities.pivot;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaRolI;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaI;
import com.icheha.aprendia_api.auth.domain.interfaces.RolI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRol implements PersonaRolI {
    
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
    private Persona persona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    // Implementación de la interfaz PersonaRolI
    @Override
    public PersonaI getPersona() {
        return persona;
    }

    @Override
    public void setPersona(PersonaI persona) {
        this.persona = (Persona) persona;
    }

    @Override
    public RolI getRol() {
        return rol;
    }

    @Override
    public void setRol(RolI rol) {
        this.rol = (Rol) rol;
    }
}
