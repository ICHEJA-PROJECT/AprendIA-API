package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.RoadType;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de RoadType
 * Define las operaciones de acceso a datos para tipos de vialidad
 */
public interface IRoadTypeRepository {
    
    /**
     * Obtiene todos los tipos de vialidad
     * @return Lista de tipos de vialidad
     */
    List<RoadType> findAll();
    
    /**
     * Busca un tipo de vialidad por su ID
     * @param id ID del tipo de vialidad
     * @return Optional con el tipo de vialidad si existe
     */
    Optional<RoadType> findById(Long id);
    
    /**
     * Guarda un tipo de vialidad
     * @param roadType Tipo de vialidad a guardar
     * @return Tipo de vialidad guardado
     */
    RoadType save(RoadType roadType);
    
    /**
     * Elimina un tipo de vialidad por su ID
     * @param id ID del tipo de vialidad a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verifica si existe un tipo de vialidad con el ID dado
     * @param id ID del tipo de vialidad
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}

