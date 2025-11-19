package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadTypeRepository extends JpaRepository<RoadTypeEntity, Long> {
    
    // Búsqueda con paginación
    @Query("SELECT r FROM RoadTypeEntity r " +
           "WHERE LOWER(r.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<RoadTypeEntity> search(@Param("search") String search, Pageable pageable);
}

