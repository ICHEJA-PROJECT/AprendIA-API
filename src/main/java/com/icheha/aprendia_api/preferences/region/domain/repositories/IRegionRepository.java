package com.icheha.aprendia_api.preferences.region.domain.repositories;

import com.icheha.aprendia_api.preferences.region.domain.entities.Region;

import java.util.List;
import java.util.Optional;

/**
 * Interface del repositorio de dominio para Region
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IRegionRepository {
    
    /**
     * Guardar una nueva región
     * @param region Región a guardar
     * @return Región guardada con ID asignado
     */
    Region save(Region region);
    
    /**
     * Buscar región por ID
     * @param id ID de la región
     * @return Región encontrada o vacío si no existe
     */
    Optional<Region> findById(Long id);
    
    /**
     * Buscar región por nombre
     * @param name Nombre de la región
     * @return Región encontrada o vacío si no existe
     */
    Optional<Region> findByName(String name);
    
    /**
     * Buscar todas las regiones
     * @return Lista de todas las regiones
     */
    List<Region> findAll();
    
    /**
     * Buscar regiones que contengan el nombre dado
     * @param name Nombre parcial a buscar
     * @return Lista de regiones que coincidan
     */
    List<Region> findByNameContaining(String name);
    
    /**
     * Verificar si existe una región con el nombre dado
     * @param name Nombre a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByName(String name);
    
    /**
     * Eliminar región por ID
     * @param id ID de la región a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verificar si existe una región con el ID dado
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
    
    /**
     * Buscar regiones por estudiante
     * @param studentId ID del estudiante
     * @return Lista de regiones asociadas al estudiante
     */
    List<Region> findByStudentId(Long studentId);
    
    /**
     * Buscar regiones por palabra
     * @param wordId ID de la palabra
     * @return Lista de regiones asociadas a la palabra
     */
    List<Region> findByWordId(Long wordId);
    
    /**
     * Buscar regiones por ejercicio
     * @param exerciseId ID del ejercicio
     * @return Lista de regiones asociadas al ejercicio
     */
    List<Region> findByExerciseId(Long exerciseId);
}

