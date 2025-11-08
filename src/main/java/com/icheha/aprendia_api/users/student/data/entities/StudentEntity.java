package com.icheha.aprendia_api.users.student.data.entities;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "educando")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educando")
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_educador", nullable = true)
    private PersonaEntity teacher;
    
    @Column(name = "qr_ruta", length = 200, nullable = false)
    private String qrPath;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_padre", nullable = false)
    private ProgenitorEntity father;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_madre", nullable = false)
    private ProgenitorEntity mother;
}

