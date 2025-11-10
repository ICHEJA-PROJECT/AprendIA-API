package com.icheha.aprendia_api.users.cell.services;

import com.icheha.aprendia_api.users.cell.data.dtos.CellResponseDto;
import com.icheha.aprendia_api.users.cell.data.dtos.CreateCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.UpdateCellDto;

import java.util.List;
import java.util.Optional;

public interface ICellService {
    
    CellResponseDto create(CreateCellDto createCellDto);
    
    List<CellResponseDto> findAll();
    
    List<CellResponseDto> findByInstitution(Long institutionId);
    
    List<CellResponseDto> findByCoordinator(Long coordinatorId);
    
    Optional<CellResponseDto> findById(Long id);
    
    CellResponseDto update(Long id, UpdateCellDto updateCellDto);
    
    void delete(Long id);
}

