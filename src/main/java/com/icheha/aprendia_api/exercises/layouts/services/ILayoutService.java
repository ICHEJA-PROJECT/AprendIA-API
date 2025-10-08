package com.icheha.aprendia_api.exercises.layouts.services;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;

import java.util.List;

public interface ILayoutService {
    
    LayoutResponseDto createLayout(CreateLayoutDto createLayoutDto);
    
    List<LayoutResponseDto> getAllLayouts();
}