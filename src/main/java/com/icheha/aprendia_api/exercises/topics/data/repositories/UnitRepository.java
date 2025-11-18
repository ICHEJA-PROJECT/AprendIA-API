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
    @Query("SELECT u FROM UnitEntity u WHERE u.nombre LIKE CONCAT('%', :nombre, '%')")
    List<UnitEntity> findByNombreContaining(@Param("nombre") String nombre);
    
    /**
     * Buscar unidad con sus relaciones cargadas (cuadernillo y temas)
     */
    @Query("SELECT u FROM UnitEntity u LEFT JOIN FETCH u.cuadernillo LEFT JOIN FETCH u.temas WHERE u.idUnidad = :id")
    Optional<UnitEntity> findByIdWithRelations(@Param("id") Long id);
    
    /**
     * Buscar todas las unidades con sus relaciones cargadas (cuadernillo y temas)
     */
    @Query("SELECT DISTINCT u FROM UnitEntity u LEFT JOIN FETCH u.cuadernillo LEFT JOIN FETCH u.temas")
    List<UnitEntity> findAllWithRelations();
    
    /**
     * Buscar unidades por cuadernillo
     */
    @Query("SELECT u FROM UnitEntity u LEFT JOIN FETCH u.cuadernillo WHERE u.idCuadernillo = :idCuadernillo")
    List<UnitEntity> findByCuadernilloId(@Param("idCuadernillo") Long idCuadernillo);
}
