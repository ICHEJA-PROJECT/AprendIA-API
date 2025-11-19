package com.icheha.aprendia_api.auth.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "id_persona")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(name = "id_persona", nullable = false, unique = true)
    private Long idPersona;
    
    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;
    
    @Column(name = "password", length = 255, nullable = true)
    private String password;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Relación OneToOne con PersonaEntity
    @OneToOne(fetch = FetchType.LAZY, targetEntity = PersonaEntity.class)
    @JoinColumn(name = "id_persona", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private PersonaEntity persona;
    
    // Relación OneToMany con UserRolEntity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<UserRolEntity> userRoles;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (isActive == null) {
            isActive = true;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

