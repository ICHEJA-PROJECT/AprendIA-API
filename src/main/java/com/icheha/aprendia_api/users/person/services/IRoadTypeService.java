package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.RoadTypeResponseDto;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;

import java.util.List;

public interface IRoadTypeService {
    List<RoadType> findAll();
    
    RoadTypeResponseDto create(CreateRoadTypeDto createRoadTypeDto);
    
    RoadTypeResponseDto findById(Long id);
    
    RoadTypeResponseDto update(Long id, UpdateRoadTypeDto updateRoadTypeDto);
    
    void deleteById(Long id);
    
    List<RoadTypeResponseDto> findAllAsDto();
}

