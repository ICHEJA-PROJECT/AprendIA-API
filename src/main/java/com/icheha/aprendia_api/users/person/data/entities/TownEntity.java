package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "ciudad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"settlements"})
@EqualsAndHashCode(exclude = {"settlements"})
public class TownEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Long id;
    
    @Column(name = "nombre", length = 128, nullable = false)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", nullable = false)
    private MunicipalityEntity municipio;
    
    @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
    private List<SettlementEntity> settlements;
}

