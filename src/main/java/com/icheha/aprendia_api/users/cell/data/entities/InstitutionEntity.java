package com.icheha.aprendia_api.users.cell.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "institucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Long id;
    
    @Column(name = "rfc", length = 15, nullable = false)
    private String rfc;
    
    @Column(name = "rct", length = 20, nullable = false)
    private String rct;
    
    @Column(name = "nombre", length = 64, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY)
    private List<CellEntity> cells;
}

