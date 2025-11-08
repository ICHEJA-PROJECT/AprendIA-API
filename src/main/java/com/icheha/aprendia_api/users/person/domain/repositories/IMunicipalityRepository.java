package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.Municipality;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de Municipality
 * Define las operaciones de acceso a datos para municipios
 */
public interface IMunicipalityRepository {
    
    /**
     * Busca municipios por estado
     * @param stateId ID del estado
     * @return Lista de municipios
     */
    List<Municipality> findByState(Long stateId);
    
    /**
     * Busca un municipio por su ID
     * @param id ID del municipio
     * @return Optional con el municipio si existe
     */
    Optional<Municipality> findById(Long id);
    
    /**
     * Obtiene todos los municipios
     * @return Lista de municipios
     */
    List<Municipality> findAll();
    
    /**
     * Guarda un municipio
     * @param municipality Municipio a guardar
     * @return Municipio guardado
     */
    Municipality save(Municipality municipality);
    
    /**
     * Elimina un municipio por su ID
     * @param id ID del municipio a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verifica si existe un municipio con el ID dado
     * @param id ID del municipio
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}

