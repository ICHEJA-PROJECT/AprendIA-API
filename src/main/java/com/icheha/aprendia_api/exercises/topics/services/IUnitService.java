package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;

import java.util.List;
import java.util.Optional;

public interface IUnitService {
    
    UnitResponseDto createUnit(CreateUnitDto createUnitDto);
    
    List<UnitResponseDto> getAllUnits();
    
    Optional<UnitResponseDto> findById(Long id);
    
    UnitResponseDto update(Long id, UpdateUnitDto updateUnitDto);
    
    void delete(Long id);
    
    List<UnitResponseDto> getUnitsByCuadernillo(Long cuadernilloId);
}
