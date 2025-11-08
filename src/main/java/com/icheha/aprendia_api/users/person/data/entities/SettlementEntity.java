package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asentamiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asentamiento")
    private Long id;
    
    @Column(name = "nombre", length = 128, nullable = false)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_codigo_postal", nullable = false)
    private ZipcodeEntity zipcode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_asentamiento", nullable = false)
    private SettlementTypeEntity settlementType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", nullable = false)
    private MunicipalityEntity municipio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ciudad", nullable = true)
    private TownEntity ciudad;
    
    // Relación temporalmente deshabilitada porque PersonaEntity.settlement está marcado como @Transient
    // ya que la columna id_asentamiento no existe en la tabla persona
    // TODO: Restaurar cuando se agregue la columna id_asentamiento a la tabla persona
    // @OneToMany(mappedBy = "settlement", fetch = FetchType.LAZY)
    // private List<PersonaEntity> personas;
}

