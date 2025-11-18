package com.icheha.aprendia_api.auth.services.impl;

import com.icheha.aprendia_api.auth.data.dtos.CreateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UpdateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UserResponseDto;
import com.icheha.aprendia_api.auth.domain.entities.User;
import com.icheha.aprendia_api.auth.domain.repositories.IUserRepository;
import com.icheha.aprendia_api.auth.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de aplicación para User
 */
@Service
public class UserServiceImpl implements IUserService {
    
    private final IUserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    @Transactional
    public UserResponseDto create(CreateUserDto createUserDto) {
        if (createUserDto == null) {
            throw new IllegalArgumentException("CreateUserDto no puede ser nulo");
        }
        
        User user = new User.Builder()
                .idPersona(createUserDto.getIdPersona())
                .username(createUserDto.getUsername())
                .isActive(createUserDto.getIsActive() != null ? createUserDto.getIsActive() : true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        User createdUser = userRepository.create(user, createUserDto.getIdPersona());
        return toResponseDto(createdUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findByIdPersona(Long idPersona) {
        if (idPersona == null) {
            return Optional.empty();
        }
        return userRepository.findByIdPersona(idPersona)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findByUsername(username)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public UserResponseDto update(Long id, UpdateUserDto updateUserDto) {
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        if (updateUserDto == null) {
            throw new IllegalArgumentException("UpdateUserDto no puede ser nulo");
        }
        
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        
        User.Builder builder = new User.Builder()
                .id(existingUser.getId())
                .idPersona(existingUser.getIdPersona())
                .username(existingUser.getUsername())
                .isActive(existingUser.getIsActive())
                .createdAt(existingUser.getCreatedAt())
                .updatedAt(LocalDateTime.now());
        
        // Actualizar solo los campos proporcionados
        if (updateUserDto.getUsername() != null) {
            builder.username(updateUserDto.getUsername());
        }
        if (updateUserDto.getIsActive() != null) {
            builder.isActive(updateUserDto.getIsActive());
        }
        
        User updatedUser = userRepository.update(builder.build());
        return toResponseDto(updatedUser);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID de usuario no puede ser nulo");
        }
        userRepository.delete(id);
    }
    
    /**
     * Convierte una entidad de dominio User a un DTO de respuesta
     */
    private UserResponseDto toResponseDto(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserResponseDto(
                user.getId(),
                user.getIdPersona(),
                user.getUsername(),
                user.getIsActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}

