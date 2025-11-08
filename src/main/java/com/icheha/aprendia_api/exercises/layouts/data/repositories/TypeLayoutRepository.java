package com.icheha.aprendia_api.exercises.layouts.data.repositories;

import com.icheha.aprendia_api.exercises.layouts.data.entities.TypeLayoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeLayoutRepository extends JpaRepository<TypeLayoutEntity, Long> {
    
    /**
     * Buscar tipo de layout por nombre
     */
    Optional<TypeLayoutEntity> findByNombre(String nombre);
    
    /**
     * Verificar si existe un tipo de layout con el nombre dado
     */
    boolean existsByNombre(String nombre);
    
    /**
     * Buscar tipos de layout que contengan el nombre dado (búsqueda parcial)
     */
    @Query("SELECT tl FROM TypeLayoutEntity tl WHERE tl.nombre LIKE CONCAT('%', :nombre, '%')")
    List<TypeLayoutEntity> findByNombreContaining(@Param("nombre") String nombre);
    
    /**
     * Buscar tipo de layout con sus relaciones cargadas
     */
    @Query("SELECT tl FROM TypeLayoutEntity tl LEFT JOIN FETCH tl.layouts WHERE tl.idTipoLayout = :id")
    Optional<TypeLayoutEntity> findByIdWithLayouts(@Param("id") Long id);
    
    /**
     * Buscar todos los tipos de layout con sus relaciones cargadas
     */
    @Query("SELECT tl FROM TypeLayoutEntity tl LEFT JOIN FETCH tl.layouts")
    List<TypeLayoutEntity> findAllWithLayouts();
}
