package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.auth.domain.valueobjects.Curp;

import java.util.Optional;

/**
 * Interfaz del repositorio de Persona para el módulo de usuarios
 * Extiende las operaciones básicas con funcionalidades específicas del módulo
 */
public interface IPersonaRepository {
    
    /**
     * Crea una nueva persona
     * @param persona Entidad de dominio Persona
     * @param roadTypeId ID del tipo de vialidad
     * @param settlementId ID del asentamiento
     * @param profileImagePath Ruta de la imagen de perfil
     * @return Persona creada
     */
    Persona create(Persona persona, Long roadTypeId, Long settlementId, String profileImagePath);
    
    /**
     * Busca una persona por su ID
     * @param id ID de la persona
     * @return Optional con la persona si existe
     */
    Optional<Persona> findById(Long id);
    
    /**
     * Busca una persona por su CURP
     * @param curp CURP de la persona
     * @return Optional con la persona si existe
     */
    Optional<Persona> findByCurp(Curp curp);
    
    /**
     * Obtiene todas las personas
     * @return Lista de todas las personas
     */
    java.util.List<Persona> findAll();
    
    /**
     * Actualiza una persona existente
     * @param persona Entidad de dominio Persona con los datos actualizados
     * @param roadTypeId ID del tipo de vialidad (opcional)
     * @param settlementId ID del asentamiento (opcional)
     * @param profileImagePath Ruta de la imagen de perfil (opcional)
     * @return Persona actualizada
     */
    Persona update(Persona persona, Long roadTypeId, Long settlementId, String profileImagePath);
    
    /**
     * Elimina una persona por su ID
     * @param id ID de la persona a eliminar
     */
    void deleteById(Long id);
}

