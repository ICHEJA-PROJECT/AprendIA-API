package com.icheha.aprendia_api.exercises.layouts.services;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateTypeLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.TypeLayoutResponseDto;

import java.util.List;

public interface ITypeLayoutService {
    
    TypeLayoutResponseDto createTypeLayout(CreateTypeLayoutDto createTypeLayoutDto);
    
    List<TypeLayoutResponseDto> getAllTypeLayouts();
}
