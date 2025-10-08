package com.icheha.aprendia_api.auth.data.entities;

import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
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
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "curp", unique = true)
    private String curp;

    @Column(name = "numero_ine")
    private String numeroIne;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private GenderEnum genero;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "estado")
    private String estado;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "vialidad_nombre")
    private String vialidadNombre;

    @Column(name = "id_vialidad_tipo")
    private Integer idVialidadTipo;

    @Column(name = "asentamiento")
    private String asentamiento;

    @Column(name = "id_asentamiento_tipo")
    private Integer idAsentamientoTipo;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PersonaRolEntity> personaRoles;
}
