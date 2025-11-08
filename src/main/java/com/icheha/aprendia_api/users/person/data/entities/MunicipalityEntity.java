package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "municipio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"towns", "settlements"})
@EqualsAndHashCode(exclude = {"towns", "settlements"})
public class MunicipalityEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Long id;
    
    @Column(name = "nombre", length = 128, nullable = false)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private StateEntity estado;
    
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<TownEntity> towns;
    
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<SettlementEntity> settlements;
}

