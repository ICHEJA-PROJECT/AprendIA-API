package com.icheha.aprendia_api.users.person.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icheha.aprendia_api.auth.domain.enums.GenderEnum;
import com.icheha.aprendia_api.users.person.data.adapters.GenderEnumConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "UserPersonaEntity")
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;
    
    @Column(name = "primer_nombre", length = 100, nullable = false)
    private String primerNombre;
    
    @Column(name = "segundo_nombre", length = 100)
    private String segundoNombre;
    
    @Column(name = "primer_apellido", length = 100, nullable = false)
    private String primerApellido;
    
    @Column(name = "segundo_apellido", length = 100)
    private String segundoApellido;
    
    @Column(name = "curp", length = 18, unique = true)
    private String curp;
    
    @Column(name = "numero_ine", length = 20)
    private String numeroIne;
    
    @Column(name = "pais", length = 100)
    private String pais;
    
    @Column(name = "nombre_rea", length = 100)
    private String nombreRea;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Convert(converter = GenderEnumConverter.class)
    @Column(name = "genero", length = 1)
    private GenderEnum genero;
    
    @Column(name = "email", length = 255)
    private String email;
    
    @Column(name = "telefono", length = 20)
    private String telefono;
    
    @Column(name = "profile_image_path", length = 500)
    private String profileImagePath;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    // Relaciones
    @OneToOne(mappedBy = "persona", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private DomicilioEntity domicilio;
    
    @OneToOne(mappedBy = "persona", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY, orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private com.icheha.aprendia_api.auth.data.entities.UserEntity user;
    
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

