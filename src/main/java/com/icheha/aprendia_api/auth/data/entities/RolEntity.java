package com.icheha.aprendia_api.auth.data.entities;

import com.icheha.aprendia_api.auth.domain.interfaces.RolI;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaRolI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad TypeORM para Rol
 * Implementa RolI y mapea a la tabla 'rol'
 */
@Entity
@Table(name = "rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolEntity implements RolI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonaRolEntity> personaRoles;

    // Implementación de la interfaz RolI
    @Override
    public List<? extends PersonaRolI> getPersonaRoles() {
        return personaRoles;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPersonaRoles(List<? extends PersonaRolI> personaRoles) {
        this.personaRoles = (List<PersonaRolEntity>) personaRoles;
    }
}
