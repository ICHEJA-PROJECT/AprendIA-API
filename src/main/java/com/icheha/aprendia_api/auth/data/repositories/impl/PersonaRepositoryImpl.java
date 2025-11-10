package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.auth.services.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementación del repositorio de Persona
 * Adapta la interfaz de dominio a la implementación de datos
 */
@Repository
public class PersonaRepositoryImpl implements IPersonaRepository {
    
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    
    public PersonaRepositoryImpl(@Lazy PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }
    
    @Override
    public Optional<Persona> findByCurp(Curp curp) {
        if (curp == null) {
            return Optional.empty();
        }
        
        return personaRepository.findByCurp(curp.getValue())
                .map(personaMapper::toDomain);
    }
    
    @Override
    public Optional<Persona> findByCurpWithRoles(Curp curp) {
        if (curp == null) {
            return Optional.empty();
        }
        
        return personaRepository.findByCurpWithRoles(curp.getValue())
                .map(personaMapper::toDomain);
    }
    
    @Override
    public Optional<Persona> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        
        return personaRepository.findById(id)
                .map(personaMapper::toDomain);
    }
    
    @Override
    public boolean existsByCurp(Curp curp) {
        if (curp == null) {
            return false;
        }
        
        return personaRepository.existsByCurp(curp.getValue());
    }
    
    @Override
    public Persona save(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("Persona no puede ser nula");
        }
        
        PersonaEntity entity = personaMapper.toEntity(persona);
        PersonaEntity savedEntity = personaRepository.save(entity);
        return personaMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        
        personaRepository.deleteById(id);
    }
}
