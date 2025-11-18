package com.icheha.aprendia_api.auth.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserEntity;
import com.icheha.aprendia_api.auth.data.mappers.UserMapper;
import com.icheha.aprendia_api.auth.data.repositories.UserRepository;
import com.icheha.aprendia_api.auth.domain.entities.User;
import com.icheha.aprendia_api.auth.domain.repositories.IUserRepository;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de dominio para User
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {
    
    private final UserRepository userRepository;
    private final PersonaRepository personaRepository;
    private final UserMapper userMapper;
    
    public UserRepositoryImpl(@Lazy UserRepository userRepository,
                             @Lazy @Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.personaRepository = personaRepository;
        this.userMapper = userMapper;
    }
    
    @Override
    public User create(User user, Long idPersona) {
        if (user == null) {
            throw new IllegalArgumentException("User no puede ser nulo");
        }
        if (idPersona == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        
        // Verificar que la persona existe
        personaRepository.findById(idPersona)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + idPersona));
        
        // Verificar que no existe otro usuario con el mismo username
        if (user.getUsername() != null && userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Ya existe un usuario con el username: " + user.getUsername());
        }
        
        // Verificar que no existe otro usuario con el mismo idPersona
        if (userRepository.existsByIdPersona(idPersona)) {
            throw new IllegalArgumentException("Ya existe un usuario para la persona con ID: " + idPersona);
        }
        
        UserEntity entity = userMapper.toEntity(user);
        entity.setIdPersona(idPersona);
        
        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id)
                .map(userMapper::toDomain);
    }
    
    @Override
    public Optional<User> findByIdPersona(Long idPersona) {
        if (idPersona == null) {
            return Optional.empty();
        }
        return userRepository.findByIdPersona(idPersona)
                .map(userMapper::toDomain);
    }
    
    @Override
    public Optional<User> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public User update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User no puede ser nulo");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo para actualizar");
        }
        
        UserEntity entity = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + user.getId()));
        
        // Actualizar campos permitidos
        if (user.getUsername() != null && !user.getUsername().equals(entity.getUsername())) {
            // Verificar que el nuevo username no esté en uso por otro usuario
            if (userRepository.existsByUsername(user.getUsername())) {
                throw new IllegalArgumentException("Ya existe un usuario con el username: " + user.getUsername());
            }
            entity.setUsername(user.getUsername());
        }
        
        if (user.getIsActive() != null) {
            entity.setIsActive(user.getIsActive());
        }
        
        UserEntity updatedEntity = userRepository.save(entity);
        return userMapper.toDomain(updatedEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + id);
        }
        
        userRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return userRepository.existsByUsername(username);
    }
    
    @Override
    public boolean existsByIdPersona(Long idPersona) {
        if (idPersona == null) {
            return false;
        }
        return userRepository.existsByIdPersona(idPersona);
    }
}

