package com.icheha.aprendia_api.users.person.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "domicilio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_domicilio")
    private Long idDomicilio;
    
    @Column(name = "id_persona", nullable = false, unique = true, insertable = true, updatable = true)
    private Long idPersona;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PersonaEntity persona;
    
    @Column(name = "calle", length = 200)
    private String calle;
    
    @Column(name = "numero_exterior", length = 20)
    private String numeroExterior;
    
    @Column(name = "colonia", length = 100)
    private String colonia;
    
    @Column(name = "localidad", length = 100)
    private String localidad;
    
    @Column(name = "id_municipio")
    private Long idMunicipio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private MunicipalityEntity municipio;
    
    @Column(name = "id_estado")
    private Long idEstado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private StateEntity estado;
    
    @Column(name = "cp", length = 5)
    private String cp;
    
}

