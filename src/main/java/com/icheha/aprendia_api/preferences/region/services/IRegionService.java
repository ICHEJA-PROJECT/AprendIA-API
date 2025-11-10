package com.icheha.aprendia_api.preferences.region.services;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.UpdateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.RegionResponseDto;

import java.util.List;

public interface IRegionService {
    
    RegionResponseDto create(CreateRegionDto dto);
    
    List<RegionResponseDto> findAll();
    
    RegionResponseDto findById(Long id);
    
    RegionResponseDto update(Long id, UpdateRegionDto updateRegionDto);
    
    void delete(Long id);
}

