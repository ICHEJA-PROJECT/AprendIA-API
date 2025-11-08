package com.icheha.aprendia_api.users.role.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.data.repositories.PersonaRolRepository;
import com.icheha.aprendia_api.auth.data.repositories.RolRepository;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.mappers.RolePersonMapper;
import com.icheha.aprendia_api.users.role.domain.repositories.IRolePersonRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RolePersonRepositoryImpl implements IRolePersonRepository {
    
    private final PersonaRolRepository personaRolRepository;
    private final com.icheha.aprendia_api.auth.data.repositories.PersonaRepository personaRepository;
    private final RolRepository rolRepository;
    private final RolePersonMapper rolePersonMapper;
    
    public RolePersonRepositoryImpl(@Lazy PersonaRolRepository personaRolRepository,
                                   @Lazy com.icheha.aprendia_api.auth.data.repositories.PersonaRepository personaRepository,
                                   @Lazy RolRepository rolRepository,
                                   RolePersonMapper rolePersonMapper) {
        this.personaRolRepository = personaRolRepository;
        this.personaRepository = personaRepository;
        this.rolRepository = rolRepository;
        this.rolePersonMapper = rolePersonMapper;
    }
    
    @Override
    public PersonaRol create(PersonaRol personaRol) {
        if (personaRol == null) {
            throw new IllegalArgumentException("PersonaRol no puede ser nulo");
        }
        if (personaRol.getIdPersona() == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        if (personaRol.getIdRol() == null) {
            throw new IllegalArgumentException("ID de rol no puede ser nulo");
        }
        
        // Validar que la persona existe
        PersonaEntity persona = personaRepository.findById(personaRol.getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + personaRol.getIdPersona()));
        
        // Validar que el rol existe
        RolEntity rol = rolRepository.findById(personaRol.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + personaRol.getIdRol()));
        
        PersonaRolEntity entity = rolePersonMapper.toEntity(personaRol);
        entity.setPersona(persona);
        entity.setRol(rol);
        
        PersonaRolEntity savedEntity = personaRolRepository.save(entity);
        return rolePersonMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<PersonaRol> createMany(List<PersonaRol> personaRoles) {
        if (personaRoles == null || personaRoles.isEmpty()) {
            return List.of();
        }
        
        return personaRoles.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<PersonaRol> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return personaRolRepository.findById(id)
                .map(rolePersonMapper::toDomain);
    }
    
    @Override
    public List<PersonaRol> findByPersonId(Long personId) {
        if (personId == null) {
            return List.of();
        }
        return personaRolRepository.findAllByPersonaId(personId).stream()
                .map(rolePersonMapper::toDomain)
                .collect(Collectors.toList());
    }
}

