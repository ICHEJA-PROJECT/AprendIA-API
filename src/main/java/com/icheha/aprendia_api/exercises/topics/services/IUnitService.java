package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateUnitDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.UnitResponseDto;

import java.util.List;

public interface IUnitService {
    
    UnitResponseDto createUnit(CreateUnitDto createUnitDto);
    
    List<UnitResponseDto> getAllUnits();
}
