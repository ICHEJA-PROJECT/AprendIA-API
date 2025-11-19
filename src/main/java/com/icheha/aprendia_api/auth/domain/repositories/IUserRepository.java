package com.icheha.aprendia_api.auth.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de dominio para User
 * Define las operaciones de persistencia para la entidad User
 */
public interface IUserRepository {
    
    /**
     * Crea un nuevo usuario
     * @param user Usuario a crear
     * @param idPersona ID de la persona asociada
     * @param hashedPassword Password hasheado (opcional, puede ser null)
     * @return Usuario creado
     */
    User create(User user, Long idPersona, String hashedPassword);
    
    /**
     * Busca un usuario por su ID
     * @param id ID del usuario
     * @return Usuario encontrado o vacío
     */
    Optional<User> findById(Long id);
    
    /**
     * Busca un usuario por el ID de la persona
     * @param idPersona ID de la persona
     * @return Usuario encontrado o vacío
     */
    Optional<User> findByIdPersona(Long idPersona);
    
    /**
     * Busca un usuario por su username
     * @param username Username del usuario
     * @return Usuario encontrado o vacío
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Obtiene todos los usuarios
     * @return Lista de usuarios
     */
    List<User> findAll();
    
    /**
     * Actualiza un usuario existente
     * @param user Usuario con los datos actualizados
     * @return Usuario actualizado
     */
    User update(User user);
    
    /**
     * Elimina un usuario por su ID
     * @param id ID del usuario a eliminar
     */
    void delete(Long id);
    
    /**
     * Verifica si existe un usuario con el username dado
     * @param username Username a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByUsername(String username);
    
    /**
     * Verifica si existe un usuario con el ID de persona dado
     * @param idPersona ID de persona a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByIdPersona(Long idPersona);
}

