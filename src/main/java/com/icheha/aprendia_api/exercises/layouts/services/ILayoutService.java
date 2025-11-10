package com.icheha.aprendia_api.exercises.layouts.services;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.UpdateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;

import java.util.List;
import java.util.Optional;

public interface ILayoutService {
    
    LayoutResponseDto createLayout(CreateLayoutDto createLayoutDto);
    
    List<LayoutResponseDto> getAllLayouts();
    
    Optional<LayoutResponseDto> findById(Long id);
    
    LayoutResponseDto update(Long id, UpdateLayoutDto updateLayoutDto);
    
    void delete(Long id);
}