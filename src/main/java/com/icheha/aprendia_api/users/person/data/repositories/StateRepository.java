package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.StateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateEntity, Long> {
    
    // Búsqueda con paginación
    @Query("SELECT s FROM StateEntity s " +
           "WHERE LOWER(s.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<StateEntity> search(@Param("search") String search, Pageable pageable);
}

