package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.Settlement;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del repositorio de Settlement
 * Define las operaciones de acceso a datos para asentamientos
 */
public interface ISettlementRepository {
    
    /**
     * Busca asentamientos por código postal
     * @param zipcode Código postal
     * @return Lista de asentamientos
     */
    List<Settlement> findByZipcode(String zipcode);
    
    /**
     * Busca asentamientos por municipio y ciudad
     * @param municipalityId ID del municipio
     * @param townId ID de la ciudad (puede ser null)
     * @return Lista de asentamientos
     */
    List<Settlement> findByMunicipalityAndTown(Long municipalityId, Long townId);
    
    /**
     * Busca asentamientos por municipio
     * @param municipalityId ID del municipio
     * @return Lista de asentamientos
     */
    List<Settlement> findByMunicipality(Long municipalityId);
    
    /**
     * Busca un asentamiento por su ID
     * @param id ID del asentamiento
     * @return Optional con el asentamiento si existe
     */
    Optional<Settlement> findById(Long id);
    
    /**
     * Obtiene todos los asentamientos
     * @return Lista de asentamientos
     */
    List<Settlement> findAll();
    
    /**
     * Guarda un asentamiento
     * @param settlement Asentamiento a guardar
     * @return Asentamiento guardado
     */
    Settlement save(Settlement settlement);
    
    /**
     * Elimina un asentamiento por su ID
     * @param id ID del asentamiento a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verifica si existe un asentamiento con el ID dado
     * @param id ID del asentamiento
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}

