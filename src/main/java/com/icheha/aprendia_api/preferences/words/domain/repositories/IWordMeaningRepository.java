package com.icheha.aprendia_api.preferences.words.domain.repositories;

import com.icheha.aprendia_api.preferences.words.domain.entities.WordMeaning;

import java.util.List;
import java.util.Optional;

/**
 * Interface del repositorio de dominio para WordMeaning
 * Define los métodos de acceso a datos desde la capa de dominio
 */
public interface IWordMeaningRepository {
    
    /**
     * Guardar un nuevo significado
     * @param wordMeaning Significado a guardar
     * @return Significado guardado con ID asignado
     */
    WordMeaning save(WordMeaning wordMeaning);
    
    /**
     * Buscar significado por ID
     * @param id ID del significado
     * @return Significado encontrado o vacío si no existe
     */
    Optional<WordMeaning> findById(Long id);
    
    /**
     * Buscar todos los significados de una palabra
     * @param wordId ID de la palabra
     * @return Lista de significados de la palabra
     */
    List<WordMeaning> findByWordId(Long wordId);
    
    /**
     * Buscar significados que contengan el texto dado
     * @param meaning Texto parcial a buscar
     * @return Lista de significados que coincidan
     */
    List<WordMeaning> findByMeaningContaining(String meaning);
    
    /**
     * Eliminar significado por ID
     * @param id ID del significado a eliminar
     */
    void deleteById(Long id);
    
    /**
     * Eliminar todos los significados de una palabra
     * @param wordId ID de la palabra
     */
    void deleteByWordId(Long wordId);
    
    /**
     * Contar significados de una palabra
     * @param wordId ID de la palabra
     * @return Número de significados
     */
    Long countByWordId(Long wordId);
    
    /**
     * Verificar si existe un significado con el ID dado
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsById(Long id);
}

