package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.RoadTypeResponseDto;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoadTypeService {
    List<RoadType> findAll();
    
    RoadTypeResponseDto create(CreateRoadTypeDto createRoadTypeDto);
    
    RoadTypeResponseDto findById(Long id);
    
    List<RoadTypeResponseDto> findAllAsDto();
    
    Page<RoadTypeResponseDto> findAll(Pageable pageable);
    
    Page<RoadTypeResponseDto> search(String search, Pageable pageable);
    
    RoadTypeResponseDto update(Long id, UpdateRoadTypeDto updateRoadTypeDto);
    
    void deleteById(Long id);
}

