package com.icheha.aprendia_api.users.role.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.data.repositories.UserRolRepository;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.data.repositories.RolRepository;
import com.icheha.aprendia_api.auth.data.mappers.UserRolMapper;
import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.domain.repositories.IRolePersonRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RolePersonRepositoryImpl implements IRolePersonRepository {
    
    private final UserRolRepository userRolRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserRolMapper userRolMapper;
    
    public RolePersonRepositoryImpl(@Lazy UserRolRepository userRolRepository,
                                   @Lazy @Qualifier("userJpaRepository") UserRepository userRepository,
                                   @Lazy RolRepository rolRepository,
                                   UserRolMapper userRolMapper) {
        this.userRolRepository = userRolRepository;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.userRolMapper = userRolMapper;
    }
    
    @Override
    public PersonaRol create(PersonaRol personaRol) {
        if (personaRol == null) {
            throw new IllegalArgumentException("PersonaRol no puede ser nulo");
        }
        if (personaRol.getIdPersona() == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        if (personaRol.getIdRol() == null) {
            throw new IllegalArgumentException("ID de rol no puede ser nulo");
        }
        
        // Validar que el usuario existe
        UserEntity user = userRepository.findById(personaRol.getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + personaRol.getIdPersona()));
        
        // Validar que el rol existe
        RolEntity rol = rolRepository.findById(personaRol.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + personaRol.getIdRol()));
        
        UserRolEntity entity = userRolMapper.toEntity(personaRol);
        entity.setUser(user);
        entity.setRol(rol);
        
        UserRolEntity savedEntity = userRolRepository.save(entity);
        return userRolMapper.toDomain(savedEntity);
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
        return userRolRepository.findById(id)
                .map(userRolMapper::toDomain);
    }
    
    @Override
    public List<PersonaRol> findByPersonId(Long userId) {
        if (userId == null) {
            return List.of();
        }
        return userRolRepository.findAllByUserId(userId).stream()
                .map(userRolMapper::toDomain)
                .collect(Collectors.toList());
    }
}

