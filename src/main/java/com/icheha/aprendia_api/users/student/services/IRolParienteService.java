package com.icheha.aprendia_api.users.student.services;

import com.icheha.aprendia_api.users.student.data.dtos.CreateRolParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.RolParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateRolParienteDto;

import java.util.List;
import java.util.Optional;

public interface IRolParienteService {
    
    RolParienteResponseDto create(CreateRolParienteDto createRolParienteDto);
    
    List<RolParienteResponseDto> findAll();
    
    Optional<RolParienteResponseDto> findById(Long id);
    
    RolParienteResponseDto update(Long id, UpdateRolParienteDto updateRolParienteDto);
    
    void delete(Long id);
}

