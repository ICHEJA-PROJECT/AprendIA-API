package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.UpdateImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.entities.Impairment;
import com.icheha.aprendia_api.preferences.impairments.data.repositories.ImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IImpairmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImpairmentServiceImpl implements IImpairmentService {
    
    @Autowired
    private ImpairmentRepository impairmentRepository;
    
    @Override
    @Transactional
    public ImpairmentResponseDto create(CreateImpairmentDto dto) {
        Impairment entity = new Impairment();
        entity.setName(dto.getName());
        
        Impairment savedEntity = impairmentRepository.save(entity);
        
        ImpairmentResponseDto response = new ImpairmentResponseDto();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());
        return response;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ImpairmentResponseDto> findAll() {
        List<Impairment> entities = impairmentRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    ImpairmentResponseDto dto = new ImpairmentResponseDto();
                    // Acceder solo a campos básicos, no a relaciones OneToMany
                    dto.setId(entity.getId());
                    dto.setName(entity.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public ImpairmentResponseDto findById(Long id) {
        Optional<Impairment> entityOpt = impairmentRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Discapacidad no encontrada con ID: " + id);
        }
        
        Impairment entity = entityOpt.get();
        ImpairmentResponseDto response = new ImpairmentResponseDto();
        response.setId(entity.getId());
        response.setName(entity.getName());
        return response;
    }
    
    @Override
    @Transactional
    public ImpairmentResponseDto update(Long id, UpdateImpairmentDto updateImpairmentDto) {
        Optional<Impairment> entityOpt = impairmentRepository.findById(id);
        if (entityOpt.isEmpty()) {
            throw new jakarta.persistence.EntityNotFoundException("Discapacidad no encontrada con ID: " + id);
        }
        
        Impairment entity = entityOpt.get();
        if (updateImpairmentDto.getName() != null && !updateImpairmentDto.getName().isEmpty()) {
            entity.setName(updateImpairmentDto.getName());
        }
        
        Impairment savedEntity = impairmentRepository.save(entity);
        
        ImpairmentResponseDto response = new ImpairmentResponseDto();
        response.setId(savedEntity.getId());
        response.setName(savedEntity.getName());
        return response;
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!impairmentRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Discapacidad no encontrada con ID: " + id);
        }
        impairmentRepository.deleteById(id);
    }
}

