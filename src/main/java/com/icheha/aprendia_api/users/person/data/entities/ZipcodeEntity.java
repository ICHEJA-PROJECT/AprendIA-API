package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "codigo_postal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"settlements"})
@EqualsAndHashCode(exclude = {"settlements"})
public class ZipcodeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_postal")
    private Long id;
    
    @Column(name = "codigo", length = 5, nullable = false)
    private String codigo;
    
    @OneToMany(mappedBy = "zipcode", fetch = FetchType.LAZY)
    private List<SettlementEntity> settlements;
}

