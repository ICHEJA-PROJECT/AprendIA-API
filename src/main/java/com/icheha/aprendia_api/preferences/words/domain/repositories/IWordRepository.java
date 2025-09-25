package com.icheha.aprendia_api.preferences.words.domain.repositories;

import com.icheha.aprendia_api.preferences.words.domain.entities.Word;

import java.util.List;
import java.util.Optional;

/**
 * Interface del repositorio de dominio para Word
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IWordRepository {
    
    /**
     * Guardar una nueva palabra
     * @param word Palabra a guardar
     * @return Palabra guardada con ID asignado
     */
    Word save(Word word);
    
    /**
     * Buscar palabra por ID
     * @param id ID de la palabra
     * @return Palabra encontrada o vacío si no existe
     */
    Optional<Word> findById(Long id);
    
    /**
     * Buscar palabra por texto exacto
     * @param word Texto de la palabra
     * @return Palabra encontrada o vacío si no existe
     */
    Optional<Word> findByWord(String word);
    
    /**
     * Buscar todas las palabras
     * @return Lista de todas las palabras
     */
    List<Word> findAll();
    
    /**
     * Buscar palabras que contengan el texto dado
     * @param word Texto parcial a buscar
     * @return Lista de palabras que coincidan
     */
    List<Word> findByWordContaining(String word);
    
    /**
     * Verificar si existe una palabra con el texto dado
     * @param word Texto a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByWord(String word);
    
    /**
     * Eliminar palabra por ID
     * @param id ID de la palabra a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Verificar si existe una palabra con el ID dado
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
    
    /**
     * Buscar palabras por ocupación
     * @param occupationId ID de la ocupación
     * @return Lista de palabras asociadas a la ocupación
     */
    List<Word> findByOccupationId(Long occupationId);
    
    /**
     * Buscar palabras por región
     * @param regionId ID de la región
     * @return Lista de palabras asociadas a la región
     */
    List<Word> findByRegionId(Long regionId);
}
