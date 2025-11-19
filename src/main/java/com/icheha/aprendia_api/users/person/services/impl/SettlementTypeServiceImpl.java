package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementTypeResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.SettlementTypeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementTypeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;
import com.icheha.aprendia_api.users.person.domain.repositories.ISettlementTypeRepository;
import com.icheha.aprendia_api.users.person.services.ISettlementTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SettlementTypeServiceImpl implements ISettlementTypeService {
    
    private final ISettlementTypeRepository settlementTypeRepository;
    private final SettlementTypeRepository settlementTypeJpaRepository;
    private final SettlementTypeMapper settlementTypeMapper;
    
    public SettlementTypeServiceImpl(ISettlementTypeRepository settlementTypeRepository,
                                    SettlementTypeRepository settlementTypeJpaRepository,
                                    SettlementTypeMapper settlementTypeMapper) {
        this.settlementTypeRepository = settlementTypeRepository;
        this.settlementTypeJpaRepository = settlementTypeJpaRepository;
        this.settlementTypeMapper = settlementTypeMapper;
    }
    
    @Override
    @Transactional
    public SettlementTypeResponseDto create(CreateSettlementTypeDto createSettlementTypeDto) {
        SettlementType settlementType = new SettlementType.Builder()
                .nombre(createSettlementTypeDto.getNombre())
                .build();
        
        SettlementType savedSettlementType = settlementTypeRepository.save(settlementType);
        return toResponseDto(savedSettlementType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public SettlementTypeResponseDto findById(Long id) {
        SettlementType settlementType = settlementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de asentamiento no encontrado con ID: " + id));
        return toResponseDto(settlementType);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SettlementTypeResponseDto> findAll() {
        return settlementTypeRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<SettlementTypeResponseDto> findAll(Pageable pageable) {
        return settlementTypeJpaRepository.findAll(pageable)
                .map(entity -> {
                    SettlementType settlementType = settlementTypeMapper.toDomain(entity);
                    return toResponseDto(settlementType);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<SettlementTypeResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return settlementTypeJpaRepository.findAll(pageable)
                    .map(entity -> {
                        SettlementType settlementType = settlementTypeMapper.toDomain(entity);
                        return toResponseDto(settlementType);
                    });
        }
        return settlementTypeJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    SettlementType settlementType = settlementTypeMapper.toDomain(entity);
                    return toResponseDto(settlementType);
                });
    }
    
    @Override
    @Transactional
    public SettlementTypeResponseDto update(Long id, UpdateSettlementTypeDto updateSettlementTypeDto) {
        SettlementType existingSettlementType = settlementTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de asentamiento no encontrado con ID: " + id));
        
        SettlementType updatedSettlementType = new SettlementType.Builder()
                .id(existingSettlementType.getId())
                .nombre(updateSettlementTypeDto.getNombre() != null ? updateSettlementTypeDto.getNombre() : existingSettlementType.getNombre())
                .build();
        
        SettlementType savedSettlementType = settlementTypeRepository.save(updatedSettlementType);
        return toResponseDto(savedSettlementType);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!settlementTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Tipo de asentamiento no encontrado con ID: " + id);
        }
        settlementTypeRepository.deleteById(id);
    }
    
    private SettlementTypeResponseDto toResponseDto(SettlementType settlementType) {
        SettlementTypeResponseDto dto = new SettlementTypeResponseDto();
        dto.setId(settlementType.getId());
        dto.setNombre(settlementType.getNombre());
        return dto;
    }
}

