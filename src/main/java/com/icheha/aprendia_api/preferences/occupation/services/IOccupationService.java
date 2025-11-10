package com.icheha.aprendia_api.preferences.occupation.services;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.UpdateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;

import java.util.List;

public interface IOccupationService {
    
    List<Object> getAllOccupations();
    
    OccupationResponseDto create(CreateOccupationDto dto);
    
    List<OccupationResponseDto> findAll();
    
    OccupationResponseDto findById(Long id);
    
    OccupationResponseDto update(Long id, UpdateOccupationDto updateOccupationDto);
    
    List<OccupationResponseDto> findByNameContaining(String name);
    
    void deleteById(Long id);
    
    boolean existsByName(String name);
}
