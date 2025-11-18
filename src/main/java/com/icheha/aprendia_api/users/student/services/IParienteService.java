package com.icheha.aprendia_api.users.student.services;

import com.icheha.aprendia_api.users.student.data.dtos.CreateParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.ParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateParienteDto;

import java.util.List;
import java.util.Optional;

public interface IParienteService {
    
    ParienteResponseDto create(CreateParienteDto createParienteDto);
    
    List<ParienteResponseDto> findAll();
    
    Optional<ParienteResponseDto> findById(Long id);
    
    List<ParienteResponseDto> findByPersonaId(Long personaId);
    
    List<ParienteResponseDto> findByParienteId(Long parienteId);
    
    List<ParienteResponseDto> findByPersonaIdAndRolNombre(Long personaId, String rolNombre);
    
    ParienteResponseDto update(Long id, UpdateParienteDto updateParienteDto);
    
    void delete(Long id);
}

