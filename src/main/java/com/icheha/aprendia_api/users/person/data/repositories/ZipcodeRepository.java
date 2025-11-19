package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.ZipcodeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipcodeRepository extends JpaRepository<ZipcodeEntity, Long> {
    
    // Consulta que no carga relaciones para evitar StackOverflowError
    @Query("SELECT DISTINCT z FROM ZipcodeEntity z")
    List<ZipcodeEntity> findAllWithoutRelations();
    
    // Búsqueda con paginación
    @Query("SELECT z FROM ZipcodeEntity z " +
           "WHERE z.codigo LIKE CONCAT('%', :search, '%')")
    Page<ZipcodeEntity> search(@Param("search") String search, Pageable pageable);
}

