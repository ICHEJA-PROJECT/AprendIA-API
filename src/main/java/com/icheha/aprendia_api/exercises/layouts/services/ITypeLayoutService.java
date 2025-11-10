package com.icheha.aprendia_api.exercises.layouts.services;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.UpdateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.TypeLayoutResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITypeLayoutService {
    
    TypeLayoutResponseDto createTypeLayout(CreateTypeLayoutDto createTypeLayoutDto);
    
    List<TypeLayoutResponseDto> getAllTypeLayouts();
    
    Optional<TypeLayoutResponseDto> findById(Long id);
    
    TypeLayoutResponseDto update(Long id, UpdateTypeLayoutDto updateTypeLayoutDto);
    
    void delete(Long id);
}
