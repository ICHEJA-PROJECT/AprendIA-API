package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.TownEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<TownEntity, Long> {
    
    @Query("SELECT t FROM TownEntity t WHERE t.municipio.id = :municipalityId")
    List<TownEntity> findByMunicipalityId(@Param("municipalityId") Long municipalityId);
    
    @Query("SELECT DISTINCT t FROM TownEntity t " +
           "LEFT JOIN FETCH t.municipio m " +
           "LEFT JOIN FETCH m.estado")
    List<TownEntity> findAllWithRelations();
    
    // Búsqueda con paginación
    @Query("SELECT t FROM TownEntity t " +
           "WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :search, '%')) " +
           "OR LOWER(t.municipio.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<TownEntity> search(@Param("search") String search, Pageable pageable);
}

