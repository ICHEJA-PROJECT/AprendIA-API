package com.icheha.aprendia_api.auth.services;

import com.icheha.aprendia_api.auth.data.dtos.CreateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UpdateUserDto;
import com.icheha.aprendia_api.auth.data.dtos.UserResponseDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del servicio de aplicación para User
 * Define las operaciones de negocio para la gestión de usuarios
 */
public interface IUserService {
    
    /**
     * Crea un nuevo usuario
     * @param createUserDto DTO con los datos del usuario a crear
     * @return DTO con los datos del usuario creado
     */
    UserResponseDto create(CreateUserDto createUserDto);
    
    /**
     * Busca un usuario por su ID
     * @param id ID del usuario
     * @return DTO con los datos del usuario encontrado o vacío
     */
    Optional<UserResponseDto> findById(Long id);
    
    /**
     * Busca un usuario por el ID de la persona
     * @param idPersona ID de la persona
     * @return DTO con los datos del usuario encontrado o vacío
     */
    Optional<UserResponseDto> findByIdPersona(Long idPersona);
    
    /**
     * Busca un usuario por su username
     * @param username Username del usuario
     * @return DTO con los datos del usuario encontrado o vacío
     */
    Optional<UserResponseDto> findByUsername(String username);
    
    /**
     * Obtiene todos los usuarios
     * @return Lista de DTOs con los datos de todos los usuarios
     */
    List<UserResponseDto> findAll();
    
    /**
     * Actualiza un usuario existente
     * @param id ID del usuario a actualizar
     * @param updateUserDto DTO con los datos a actualizar
     * @return DTO con los datos del usuario actualizado
     */
    UserResponseDto update(Long id, UpdateUserDto updateUserDto);
    
    /**
     * Elimina un usuario por su ID
     * @param id ID del usuario a eliminar
     */
    void delete(Long id);
}

