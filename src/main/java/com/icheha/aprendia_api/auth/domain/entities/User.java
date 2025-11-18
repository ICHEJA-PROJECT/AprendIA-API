package com.icheha.aprendia_api.auth.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad de dominio User (Usuario)
 * Representa un usuario del sistema con credenciales de acceso
 */
public class User {
    
    private final Long id;
    private final Long idPersona;
    private final String username;
    private final Boolean isActive;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    
    private User(Builder builder) {
        this.id = builder.id;
        this.idPersona = builder.idPersona;
        this.username = builder.username;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public Long getIdPersona() {
        return idPersona;
    }
    
    public String getUsername() {
        return username;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public boolean isActive() {
        return isActive != null && isActive;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && 
               Objects.equals(idPersona, user.idPersona) &&
               Objects.equals(username, user.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, idPersona, username);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idPersona=" + idPersona +
                ", username='" + username + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    
    public static class Builder {
        private Long id;
        private Long idPersona;
        private String username;
        private Boolean isActive;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder idPersona(Long idPersona) {
            this.idPersona = idPersona;
            return this;
        }
        
        public Builder username(String username) {
            this.username = username;
            return this;
        }
        
        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        
        public Builder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
}

