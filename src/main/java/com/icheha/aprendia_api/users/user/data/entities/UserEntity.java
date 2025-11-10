package com.icheha.aprendia_api.users.user.data.entities;

import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;
    
    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;
    
    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;
    
    @Column(name = "id_rol", nullable = false)
    private Long idRol;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private RolEntity rol;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}

