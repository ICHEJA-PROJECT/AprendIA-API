package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateRoadTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.RoadTypeResponseDto;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;
import com.icheha.aprendia_api.users.person.domain.repositories.IRoadTypeRepository;
import com.icheha.aprendia_api.users.person.services.IRoadTypeService;
import com.icheha.aprendia_api.users.person.data.repositories.RoadTypeRepository;
import com.icheha.aprendia_api.users.person.data.mappers.RoadTypeMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoadTypeServiceImpl implements IRoadTypeService {
    
    private final IRoadTypeRepository roadTypeRepository;
    private final RoadTypeRepository roadTypeJpaRepository;
    private final RoadTypeMapper roadTypeMapper;
    
    public RoadTypeServiceImpl(IRoadTypeRepository roadTypeRepository,
                              RoadTypeRepository roadTypeJpaRepository,
                              RoadTypeMapper roadTypeMapper) {
        this.roadTypeRepository = roadTypeRepository;
        this.roadTypeJpaRepository = roadTypeJpaRepository;
        this.roadTypeMapper = roadTypeMapper;
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
    @Transactional(readOnly = true)
    public Page<RoadTypeResponseDto> findAll(Pageable pageable) {
        return roadTypeJpaRepository.findAll(pageable)
                .map(entity -> {
                    RoadType roadType = roadTypeMapper.toDomain(entity);
                    return toResponseDto(roadType);
                });
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
    @Transactional(readOnly = true)
    public Page<RoadTypeResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return roadTypeJpaRepository.findAll(pageable)
                    .map(entity -> {
                        RoadType roadType = roadTypeMapper.toDomain(entity);
                        return toResponseDto(roadType);
                    });
        }
        return roadTypeJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    RoadType roadType = roadTypeMapper.toDomain(entity);
                    return toResponseDto(roadType);
                });
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

