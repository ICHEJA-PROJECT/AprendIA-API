package com.icheha.aprendia_api.exercises.topics.data.repositories;

import com.icheha.aprendia_api.exercises.topics.data.entities.CuadernilloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuadernilloRepository extends JpaRepository<CuadernilloEntity, Long> {
    
    @Query("SELECT c FROM CuadernilloEntity c " +
           "LEFT JOIN FETCH c.rutaAprendizaje " +
           "WHERE c.idCuadernillo = :id")
    Optional<CuadernilloEntity> findByIdWithRelations(@Param("id") Long id);
    
    @Query("SELECT c FROM CuadernilloEntity c " +
           "LEFT JOIN FETCH c.rutaAprendizaje " +
           "WHERE c.idRutaAprendizaje = :idRutaAprendizaje")
    List<CuadernilloEntity> findByRutaAprendizaje(@Param("idRutaAprendizaje") Long idRutaAprendizaje);
    
    @Query("SELECT c FROM CuadernilloEntity c " +
           "LEFT JOIN FETCH c.rutaAprendizaje")
    List<CuadernilloEntity> findAllWithRelations();
    
    @Query("SELECT c FROM CuadernilloEntity c " +
           "LEFT JOIN FETCH c.rutaAprendizaje " +
           "WHERE c.nombre LIKE CONCAT('%', :nombre, '%')")
    List<CuadernilloEntity> findByNombreContaining(@Param("nombre") String nombre);
}

