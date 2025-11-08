package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.Town;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de Town
 * Define las operaciones de acceso a datos para ciudades
 */
public interface ITownRepository {
    
    /**
     * Busca ciudades por municipio
     * @param municipalityId ID del municipio
     * @return Lista de ciudades
     */
    List<Town> findByMunicipality(Long municipalityId);
    
    /**
     * Busca una ciudad por su ID
     * @param id ID de la ciudad
     * @return Optional con la ciudad si existe
     */
    Optional<Town> findById(Long id);
    
    /**
     * Obtiene todas las ciudades
     * @return Lista de ciudades
     */
    List<Town> findAll();
    
    /**
     * Guarda una ciudad
     * @param town Ciudad a guardar
     * @return Ciudad guardada
     */
    Town save(Town town);
    
    /**
     * Elimina una ciudad por su ID
     * @param id ID de la ciudad a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verifica si existe una ciudad con el ID dado
     * @param id ID de la ciudad
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}

