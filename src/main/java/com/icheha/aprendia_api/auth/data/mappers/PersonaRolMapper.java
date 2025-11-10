package com.icheha.aprendia_api.auth.data.mappers;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonaRolMapper {
    
    @Autowired
    private RoleMapper roleMapper;
    
    public PersonaRol toDomain(PersonaRolEntity entity) {
        if (entity == null) return null;
        
        PersonaRol.Builder builder = new PersonaRol.Builder()
                .idPersonaRol(entity.getIdPersonaRol())
                .idPersona(entity.getIdPersona())
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
    
    public PersonaRolEntity toEntity(PersonaRol domain) {
        if (domain == null) return null;
        
        PersonaRolEntity entity = new PersonaRolEntity();
        entity.setIdPersonaRol(domain.getIdPersonaRol());
        entity.setIdPersona(domain.getIdPersona());
        entity.setIdRol(domain.getIdRol());
        // Note: persona and rol relationships should be set separately
        return entity;
    }
}
