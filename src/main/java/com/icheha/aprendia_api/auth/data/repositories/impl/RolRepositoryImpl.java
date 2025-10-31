package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.data.mappers.PersonaRolMapper;
import com.icheha.aprendia_api.auth.data.repositories.PersonaRolRepository;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RolRepositoryImpl implements IRolRepository {
    
    @Autowired
    private PersonaRolRepository personaRolRepository;
    
    @Autowired
    private PersonaRolMapper personaRolMapper;
    
    @Override
    public Optional<PersonaRol> findByPersonaId(Long personaId) {
        return personaRolRepository.findByPersonaId(personaId)
                .map(personaRolMapper::toDomain);
    }
    
    @Override
    public Optional<PersonaRol> findById(Long id) {
        return personaRolRepository.findById(id)
                .map(personaRolMapper::toDomain);
    }
    
    @Override
    public PersonaRol save(PersonaRol personaRol) {
        PersonaRolEntity entity = personaRolMapper.toEntity(personaRol);
        PersonaRolEntity savedEntity = personaRolRepository.save(entity);
        return personaRolMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        personaRolRepository.deleteById(id);
    }
    
    @Override
    public void deleteByPersonaId(Long personaId) {
        personaRolRepository.deleteByPersonaId(personaId);
    }
}
