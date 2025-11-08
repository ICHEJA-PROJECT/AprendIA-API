package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<SettlementEntity, Long> {
    
    @Query("SELECT DISTINCT s FROM SettlementEntity s " +
           "LEFT JOIN FETCH s.zipcode " +
           "LEFT JOIN FETCH s.settlementType " +
           "LEFT JOIN FETCH s.municipio m " +
           "LEFT JOIN FETCH m.estado " +
           "LEFT JOIN FETCH s.ciudad " +
           "WHERE s.zipcode.codigo = :zipcode")
    List<SettlementEntity> findByZipcode(@Param("zipcode") String zipcode);
    
    @Query("SELECT DISTINCT s FROM SettlementEntity s " +
           "LEFT JOIN FETCH s.zipcode " +
           "LEFT JOIN FETCH s.settlementType " +
           "LEFT JOIN FETCH s.municipio m " +
           "LEFT JOIN FETCH m.estado " +
           "LEFT JOIN FETCH s.ciudad " +
           "WHERE s.municipio.id = :municipalityId")
    List<SettlementEntity> findByMunicipalityId(@Param("municipalityId") Long municipalityId);
    
    @Query("SELECT DISTINCT s FROM SettlementEntity s " +
           "LEFT JOIN FETCH s.zipcode " +
           "LEFT JOIN FETCH s.settlementType " +
           "LEFT JOIN FETCH s.municipio m " +
           "LEFT JOIN FETCH m.estado " +
           "LEFT JOIN FETCH s.ciudad " +
           "WHERE s.municipio.id = :municipalityId AND s.ciudad.id = :townId")
    List<SettlementEntity> findByMunicipalityIdAndTownId(@Param("municipalityId") Long municipalityId, @Param("townId") Long townId);
    
    @Query("SELECT DISTINCT s FROM SettlementEntity s " +
           "LEFT JOIN FETCH s.zipcode " +
           "LEFT JOIN FETCH s.settlementType " +
           "LEFT JOIN FETCH s.municipio m " +
           "LEFT JOIN FETCH m.estado")
    List<SettlementEntity> findAllWithRelations();
}

