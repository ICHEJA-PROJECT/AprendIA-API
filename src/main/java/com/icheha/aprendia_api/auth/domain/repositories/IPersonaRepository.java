package com.icheha.aprendia_api.auth.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;

import java.util.Optional;

/**
 * Interface del repositorio de Persona en el dominio
 * Define las operaciones de acceso a datos para la entidad Persona
 * Sigue el principio de inversión de dependencias
 */
public interface IPersonaRepository {
    
    /**
     * Busca una persona por su CURP
     * @param curp CURP de la persona
     * @return Optional con la persona si existe
     */
    Optional<Persona> findByCurp(Curp curp);
    
    /**
     * Busca una persona por su CURP incluyendo sus roles
     * @param curp CURP de la persona
     * @return Optional con la persona y sus roles si existe
     */
    Optional<Persona> findByCurpWithRoles(Curp curp);
    
    /**
     * Busca una persona por su ID
     * @param id ID de la persona
     * @return Optional con la persona si existe
     */
    Optional<Persona> findById(Long id);
    
    /**
     * Verifica si existe una persona con el CURP dado
     * @param curp CURP a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByCurp(Curp curp);
    
    /**
     * Guarda una persona
     * @param persona Persona a guardar
     * @return Persona guardada
     */
    Persona save(Persona persona);
    
    /**
     * Elimina una persona por su ID
     * @param id ID de la persona a eliminar
     */
    void deleteById(Long id);
}

