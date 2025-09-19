package com.icheha.aprendia_api.auth.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.pivot.PersonaRol;
import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaI;
import com.icheha.aprendia_api.auth.domain.interfaces.PersonaRolI;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements PersonaI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "primer_nombre", length = 32, nullable = false)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 32)
    private String segundoNombre;

    @Column(name = "apellido_paterno", length = 32, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 32, nullable = false)
    private String apellidoMaterno;

    @Column(name = "curp", length = 18, nullable = false, unique = true)
    private String curp;

    @Column(name = "numero_ine", length = 13, nullable = false)
    private String numeroIne;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private GenderEnum genero;

    @Column(name = "codigo_postal", length = 5, nullable = false)
    private String codigoPostal;

    @Column(name = "estado", length = 100, nullable = false)
    private String estado;

    @Column(name = "municipio", length = 100, nullable = false)
    private String municipio;

    @Column(name = "localidad", length = 100, nullable = false)
    private String localidad;

    @Column(name = "vialidad_nombre", length = 100, nullable = false)
    private String vialidadNombre;

    @Column(name = "id_vialidad_tipo", nullable = false)
    private Integer idVialidadTipo;

    @Column(name = "asentamiento", length = 100, nullable = false)
    private String asentamiento;

    @Column(name = "id_asentamiento_tipo", nullable = false)
    private Integer idAsentamientoTipo;

    @Column(name = "password", length = 254, nullable = false)
    private String password;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonaRol> personaRoles;

    // Implementación de la interfaz PersonaI
    @Override
    public List<? extends PersonaRolI> getPersonaRoles() {
        return personaRoles;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setPersonaRoles(List<? extends PersonaRolI> personaRoles) {
        this.personaRoles = (List<PersonaRol>) personaRoles;
    }
}