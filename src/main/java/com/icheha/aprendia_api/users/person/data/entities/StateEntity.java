package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"municipalities"})
@EqualsAndHashCode(exclude = {"municipalities"})
public class StateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;
    
    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;
    
    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<MunicipalityEntity> municipalities;
}

