package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {
    
    /**
     * Buscar unidad por nombre
     */
    Optional<UnitEntity> findByNombre(String nombre);
    
    /**
     * Verificar si existe una unidad con el nombre dado
     */
    boolean existsByNombre(String nombre);
    
    /**
     * Buscar unidades que contengan el nombre dado (búsqueda parcial)
     */
    @Query("SELECT u FROM UnitEntity u WHERE u.nombre LIKE %:nombre%")
    List<UnitEntity> findByNombreContaining(@Param("nombre") String nombre);
    
    /**
     * Buscar unidad con sus relaciones cargadas
     */
    @Query("SELECT u FROM UnitEntity u LEFT JOIN FETCH u.temas WHERE u.idUnidad = :id")
    Optional<UnitEntity> findByIdWithTopics(@Param("id") Long id);
    
    /**
     * Buscar todas las unidades con sus relaciones cargadas
     */
    @Query("SELECT u FROM UnitEntity u LEFT JOIN FETCH u.temas")
    List<UnitEntity> findAllWithTopics();
}
