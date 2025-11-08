package com.icheha.aprendia_api.users.cell.data.repositories;

import com.icheha.aprendia_api.users.cell.data.entities.CellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CellRepository extends JpaRepository<CellEntity, Long> {
    
    @Query("SELECT c FROM CellEntity c JOIN FETCH c.coordinator WHERE c.institution.id = :institutionId")
    List<CellEntity> findByInstitutionIdWithCoordinator(@Param("institutionId") Long institutionId);
    
    @Query("SELECT c FROM CellEntity c WHERE c.coordinator.idPersona = :coordinatorId")
    List<CellEntity> findByCoordinatorId(@Param("coordinatorId") Long coordinatorId);
    
    @Query("SELECT c FROM CellEntity c " +
           "JOIN FETCH c.coordinator " +
           "LEFT JOIN FETCH c.teachers t " +
           "LEFT JOIN FETCH t.teacher " +
           "WHERE c.id = :id")
    Optional<CellEntity> findByIdWithRelations(@Param("id") Long id);
}

