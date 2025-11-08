package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tipo_asentamiento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"settlements"})
@EqualsAndHashCode(exclude = {"settlements"})
public class SettlementTypeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_asentamiento")
    private Long id;
    
    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;
    
    @OneToMany(mappedBy = "settlementType", fetch = FetchType.LAZY)
    private List<SettlementEntity> settlements;
}

