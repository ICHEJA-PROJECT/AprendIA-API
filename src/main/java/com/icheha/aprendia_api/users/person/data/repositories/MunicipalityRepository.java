package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.MunicipalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipalityRepository extends JpaRepository<MunicipalityEntity, Long> {
    
    @Query("SELECT m FROM MunicipalityEntity m WHERE m.estado.id = :stateId")
    List<MunicipalityEntity> findByStateId(@Param("stateId") Long stateId);
    
    @Query("SELECT DISTINCT m FROM MunicipalityEntity m " +
           "LEFT JOIN FETCH m.estado")
    List<MunicipalityEntity> findAllWithRelations();
}

