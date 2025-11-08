package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.RoadTypeResponseDto;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;
import com.icheha.aprendia_api.users.person.domain.repositories.IRoadTypeRepository;
import com.icheha.aprendia_api.users.person.services.IRoadTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadTypeServiceImpl implements IRoadTypeService {
    
    private final IRoadTypeRepository roadTypeRepository;
    
    public RoadTypeServiceImpl(IRoadTypeRepository roadTypeRepository) {
        this.roadTypeRepository = roadTypeRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RoadType> findAll() {
        return roadTypeRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RoadTypeResponseDto> findAllAsDto() {
        List<RoadType> roadTypes = roadTypeRepository.findAll();
        return roadTypes.stream()
                .map(roadType -> toResponseDto(roadType))
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public RoadTypeResponseDto create(CreateRoadTypeDto createRoadTypeDto) {
        RoadType roadType = new RoadType.Builder()
                .nombre(createRoadTypeDto.getNombre())
                .build();
        
        RoadType savedRoadType = roadTypeRepository.save(roadType);
        return toResponseDto(savedRoadType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public RoadTypeResponseDto findById(Long id) {
        RoadType roadType = roadTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de vialidad no encontrado con ID: " + id));
        return toResponseDto(roadType);
    }
    
    @Override
    @Transactional
    public RoadTypeResponseDto update(Long id, UpdateRoadTypeDto updateRoadTypeDto) {
        RoadType existingRoadType = roadTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de vialidad no encontrado con ID: " + id));
        
        RoadType.Builder builder = new RoadType.Builder()
                .id(existingRoadType.getId())
                .nombre(updateRoadTypeDto.getNombre() != null ? updateRoadTypeDto.getNombre() : existingRoadType.getNombre());
        
        RoadType updatedRoadType = roadTypeRepository.save(builder.build());
        return toResponseDto(updatedRoadType);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!roadTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo de vialidad no encontrado con ID: " + id);
        }
        roadTypeRepository.deleteById(id);
    }
    
    private RoadTypeResponseDto toResponseDto(RoadType roadType) {
        if (roadType == null) return null;
        
        RoadTypeResponseDto dto = new RoadTypeResponseDto();
        dto.setId(roadType.getId());
        dto.setNombre(roadType.getNombre());
        return dto;
    }
}

