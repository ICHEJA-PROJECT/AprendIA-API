package com.icheha.aprendia_api.auth.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;

import java.util.Optional;

/**
 * Interface del repositorio de Rol en el dominio
 * Define las operaciones de acceso a datos para roles y relaciones persona-rol
 * Sigue el principio de inversión de dependencias
 */
public interface IRolRepository {
    
    /**
     * Busca un PersonaRol por el ID de la persona
     * @param personaId ID de la persona
     * @return Optional con el PersonaRol si existe
     */
    Optional<PersonaRol> findByPersonaId(Long personaId);
    
    /**
     * Busca un PersonaRol por su ID
     * @param id ID del PersonaRol
     * @return Optional con el PersonaRol si existe
     */
    Optional<PersonaRol> findById(Long id);
    
    /**
     * Guarda un PersonaRol
     * @param personaRol PersonaRol a guardar
     * @return PersonaRol guardado
     */
    PersonaRol save(PersonaRol personaRol);
    
    /**
     * Elimina un PersonaRol por su ID
     * @param id ID del PersonaRol a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Elimina todos los PersonaRol de una persona
     * @param personaId ID de la persona
     */
    void deleteByPersonaId(Long personaId);
}

