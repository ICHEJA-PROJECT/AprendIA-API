package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.repositories.IRolRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementación del repositorio de Rol
 * Adapta la interfaz de dominio a la implementación de datos
 */
@Repository
public class RolRepositoryImpl implements IRolRepository {
    
    private final PersonaRolRepository personaRolRepository;
    
    public RolRepositoryImpl(@Lazy PersonaRolRepository personaRolRepository) {
        this.personaRolRepository = personaRolRepository;
    }
    
    @Override
    public Optional<PersonaRol> findByPersonaId(Long personaId) {
        if (personaId == null) {
            return Optional.empty();
        }
        
        return personaRolRepository.findByPersonaId(personaId)
                .map(this::mapToDomain);
    }
    
    @Override
    public Optional<PersonaRol> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        
        return personaRolRepository.findById(id)
                .map(this::mapToDomain);
    }
    
    @Override
    public PersonaRol save(PersonaRol personaRol) {
        if (personaRol == null) {
            throw new IllegalArgumentException("PersonaRol no puede ser nulo");
        }
        
        PersonaRolEntity entity = mapToEntity(personaRol);
        PersonaRolEntity savedEntity = personaRolRepository.save(entity);
        return mapToDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        
        personaRolRepository.deleteById(id);
    }
    
    @Override
    public void deleteByPersonaId(Long personaId) {
        if (personaId == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        
        personaRolRepository.deleteByPersonaId(personaId);
    }
    
    /**
     * Mapea PersonaRolEntity a PersonaRol de dominio
     */
    private PersonaRol mapToDomain(PersonaRolEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new PersonaRol.Builder()
                .idPersonaRol(entity.getIdPersonaRol())
                .idPersona(entity.getIdPersona())
                .idRol(entity.getIdRol())
                .rol(entity.getRol() != null ? 
                    new com.icheha.aprendia_api.auth.domain.entities.Rol.Builder()
                        .idRol(entity.getRol().getIdRol())
                        .nombre(entity.getRol().getNombre())
                        .build() : null)
                .build();
    }
    
    /**
     * Mapea PersonaRol de dominio a PersonaRolEntity
     */
    private PersonaRolEntity mapToEntity(PersonaRol personaRol) {
        if (personaRol == null) {
            return null;
        }
        
        PersonaRolEntity entity = new PersonaRolEntity();
        entity.setIdPersonaRol(personaRol.getIdPersonaRol());
        entity.setIdPersona(personaRol.getIdPersona());
        entity.setIdRol(personaRol.getIdRol());
        
        if (personaRol.getRol() != null) {
            com.icheha.aprendia_api.auth.data.entities.RolEntity rolEntity = 
                new com.icheha.aprendia_api.auth.data.entities.RolEntity();
            rolEntity.setIdRol(personaRol.getRol().getIdRol());
            rolEntity.setNombre(personaRol.getRol().getNombre());
            entity.setRol(rolEntity);
        }
        
        return entity;
    }
}
