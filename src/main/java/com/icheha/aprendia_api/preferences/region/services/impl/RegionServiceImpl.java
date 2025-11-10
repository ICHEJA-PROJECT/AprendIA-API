package com.icheha.aprendia_api.preferences.region.services.impl;

import com.icheha.aprendia_api.preferences.region.data.dtos.request.CreateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.request.UpdateRegionDto;
import com.icheha.aprendia_api.preferences.region.data.dtos.response.RegionResponseDto;
import com.icheha.aprendia_api.preferences.region.data.entities.RegionEntity;
import com.icheha.aprendia_api.preferences.region.data.repositories.RegionRepository;
import com.icheha.aprendia_api.preferences.region.services.IRegionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional(readOnly = true)
    public RegionResponseDto findById(Long id) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada con ID: " + id));
        
        RegionResponseDto response = new RegionResponseDto();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
    
    @Override
    @Transactional
    public RegionResponseDto update(Long id, UpdateRegionDto updateRegionDto) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada con ID: " + id));
        
        if (updateRegionDto.getName() != null && !updateRegionDto.getName().trim().isEmpty()) {
            entity.setName(updateRegionDto.getName());
        }
        
        RegionEntity updatedEntity = regionRepository.save(entity);
        RegionResponseDto response = new RegionResponseDto();
        response.setId(updatedEntity.getId());
        response.setName(updatedEntity.getName());
        return response;
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        RegionEntity entity = regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada con ID: " + id));
        regionRepository.delete(entity);
    }
}

