package com.icheha.aprendia_api.users.cell.domain.repositories;

import com.icheha.aprendia_api.users.cell.domain.entities.Cell;

import java.util.List;
import java.util.Optional;

public interface ICellRepository {
    
    Cell create(Cell cell, Long institutionId, Long coordinatorId);
    
    List<Cell> findAll();
    
    List<Cell> findByInstitution(Long institutionId);
    
    List<Cell> findByCoordinator(Long coordinatorId);
    
    Optional<Cell> findById(Long id);
    
    Cell update(Cell cell, Long institutionId, Long coordinatorId);
    
    void delete(Long id);
}

