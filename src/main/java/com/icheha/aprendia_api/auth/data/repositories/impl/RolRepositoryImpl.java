package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.data.repositories.UserRolRepository;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.auth.domain.repositories.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RolRepositoryImpl implements IRolRepository {
    
    @Autowired
    private UserRolRepository userRolRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRolMapper userRolMapper;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonaRol> findByPersonaId(Long personaId) {
        // Buscar el usuario por idPersona y luego obtener sus roles
        return userRepository.findByIdPersona(personaId)
                .flatMap(user -> userRolRepository.findAllByUserId(user.getIdUser()).stream()
                        .map(userRolMapper::toDomain)
                        .findFirst());
    }
    
    @Override
    public Optional<PersonaRol> findById(Long id) {
        return userRolRepository.findById(id)
                .map(userRolMapper::toDomain);
    }
    
    @Override
    public PersonaRol save(PersonaRol personaRol) {
        UserRolEntity entity = userRolMapper.toEntity(personaRol);
        // Necesitamos establecer la relación con UserEntity
        if (personaRol.getIdPersona() != null) {
            userRepository.findById(personaRol.getIdPersona())
                    .ifPresent(entity::setUser);
        }
        UserRolEntity savedEntity = userRolRepository.save(entity);
        return userRolMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        userRolRepository.deleteById(id);
    }
    
    @Override
    public void deleteByPersonaId(Long personaId) {
        // Buscar el usuario por idPersona y eliminar sus roles
        userRepository.findByIdPersona(personaId)
                .ifPresent(user -> userRolRepository.deleteByUserId(user.getIdUser()));
    }
}
