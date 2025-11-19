package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementTypeRepository extends JpaRepository<SettlementTypeEntity, Long> {
    
    // Consulta que no carga relaciones para evitar StackOverflowError
    @Query("SELECT DISTINCT st FROM SettlementTypeEntity st")
    List<SettlementTypeEntity> findAllWithoutRelations();
    
    // Búsqueda con paginación
    @Query("SELECT st FROM SettlementTypeEntity st " +
           "WHERE LOWER(st.nombre) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<SettlementTypeEntity> search(@Param("search") String search, Pageable pageable);
}

