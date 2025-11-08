package com.icheha.aprendia_api.preferences.region.services.impl;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.RegionResponseDto;
import com.icheha.aprendia_api.preferences.region.data.entities.RegionEntity;
import com.icheha.aprendia_api.preferences.region.data.repositories.RegionRepository;
import com.icheha.aprendia_api.preferences.region.services.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionServiceImpl implements IRegionService {
    
    @Autowired
    private RegionRepository regionRepository;
    
    @Override
    public RegionResponseDto create(CreateRegionDto dto) {
        RegionEntity entity = new RegionEntity();
        entity.setName(dto.getName());
        
        RegionEntity savedEntity = regionRepository.save(entity);
        
        RegionResponseDto response = new RegionResponseDto();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());
        return response;
    }
    
    @Override
    public List<RegionResponseDto> findAll() {
        List<RegionEntity> entities = regionRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    RegionResponseDto dto = new RegionResponseDto();
                    dto.setId(entity.getId());
                    dto.setName(entity.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public RegionResponseDto findById(Long id) {
        Optional<RegionEntity> entityOpt = regionRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new RuntimeException("Región no encontrada con ID: " + id);
        }
        
        RegionEntity entity = entityOpt.get();
        RegionResponseDto response = new RegionResponseDto();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
}

