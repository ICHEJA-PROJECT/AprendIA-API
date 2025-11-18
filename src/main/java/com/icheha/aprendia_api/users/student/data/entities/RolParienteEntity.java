package com.icheha.aprendia_api.users.student.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rol_pariente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolParienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_pariente")
    private Long id;
    
    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;
    
    @OneToMany(mappedBy = "rolPariente", fetch = FetchType.LAZY)
    private List<ParienteEntity> parientes;
}

