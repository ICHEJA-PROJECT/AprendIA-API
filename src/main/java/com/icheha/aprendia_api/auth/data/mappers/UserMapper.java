package com.icheha.aprendia_api.auth.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.domain.entities.User;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre UserEntity (capa de datos) y User (capa de dominio)
 */
@Component
public class UserMapper {
    
    /**
     * Convierte una entidad de dominio User a una entidad de datos UserEntity
     * @param user Entidad de dominio
     * @return Entidad de datos
     */
    public UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        
        UserEntity entity = new UserEntity();
        entity.setIdUser(user.getId());
        entity.setIdPersona(user.getIdPersona());
        entity.setUsername(user.getUsername());
        entity.setIsActive(user.getIsActive());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        
        return entity;
    }
    
    /**
     * Convierte una entidad de datos UserEntity a una entidad de dominio User
     * @param entity Entidad de datos
     * @return Entidad de dominio
     */
    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new User.Builder()
                .id(entity.getIdUser())
                .idPersona(entity.getIdPersona())
                .username(entity.getUsername())
                .isActive(entity.getIsActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}

