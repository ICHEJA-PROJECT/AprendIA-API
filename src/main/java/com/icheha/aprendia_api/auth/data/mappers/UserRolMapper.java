package com.icheha.aprendia_api.auth.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRolMapper {
    
    @Autowired
    private RoleMapper roleMapper;
    
    public PersonaRol toDomain(UserRolEntity entity) {
        if (entity == null) return null;
        
        PersonaRol.Builder builder = new PersonaRol.Builder()
                .idPersonaRol(entity.getIdUserRol())
                .idPersona(entity.getIdUser()) // Mapear idUser a idPersona temporalmente para compatibilidad
                .idRol(entity.getIdRol());
        
        // Mapear el rol si está disponible (cargado con JOIN FETCH)
        // Manejar LazyInitializationException de forma segura
        try {
            if (entity.getRol() != null) {
                builder.rol(roleMapper.toDomain(entity.getRol()));
            }
        } catch (org.hibernate.LazyInitializationException e) {
            // Si la relación no está cargada, dejar el rol como null
            // Esto puede ocurrir si la consulta no carga la relación con JOIN FETCH
            builder.rol(null);
        } catch (Exception e) {
            // Manejar cualquier otra excepción de forma segura
            builder.rol(null);
        }
        
        return builder.build();
    }
    
    public UserRolEntity toEntity(PersonaRol domain) {
        if (domain == null) return null;
        
        UserRolEntity entity = new UserRolEntity();
        entity.setIdUserRol(domain.getIdPersonaRol());
        entity.setIdUser(domain.getIdPersona()); // Mapear idPersona a idUser temporalmente para compatibilidad
        entity.setIdRol(domain.getIdRol());
        // Note: user and rol relationships should be set separately
        return entity;
    }
}

