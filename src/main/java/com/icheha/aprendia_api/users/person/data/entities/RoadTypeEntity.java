package com.icheha.aprendia_api.users.person.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tipo_vialidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RoadTypeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_vialidad")
    private Long id;
    
    @Column(name = "nombre", length = 64, nullable = false)
    private String nombre;
    
    // Relaciones
    // Nota: La relación con PersonaEntity se eliminó porque PersonaEntity no tiene la propiedad 'roadType'
    // Si se necesita esta relación en el futuro, agregar la propiedad 'roadType' a PersonaEntity o DomicilioEntity
    // @OneToMany(mappedBy = "roadType", fetch = FetchType.LAZY)
    // private List<PersonaEntity> personas;
}

