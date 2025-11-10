package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.MetodologiaResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.MetodologiaEntity;
import com.icheha.aprendia_api.exercises.topics.data.repositories.MetodologiaRepository;
import com.icheha.aprendia_api.exercises.topics.services.IMetodologiaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetodologiaServiceImpl implements IMetodologiaService {
    
    private final MetodologiaRepository metodologiaRepository;
    
    @Autowired
    public MetodologiaServiceImpl(MetodologiaRepository metodologiaRepository) {
        this.metodologiaRepository = metodologiaRepository;
    }
    
    @Override
    @Transactional
    public MetodologiaResponseDto create(CreateMetodologiaDto createMetodologiaDto) {
        MetodologiaEntity entity = new MetodologiaEntity();
        entity.setNombre(createMetodologiaDto.getNombre());
        entity.setDescripcion(createMetodologiaDto.getDescripcion());
        
        MetodologiaEntity savedEntity = metodologiaRepository.save(entity);
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MetodologiaResponseDto> findAll() {
        return metodologiaRepository.findAllWithRelations().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<MetodologiaResponseDto> findById(Long id) {
        return metodologiaRepository.findByIdWithRelations(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MetodologiaResponseDto> findByNombreContaining(String nombre) {
        return metodologiaRepository.findByNombreContaining(nombre).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public MetodologiaResponseDto update(Long id, UpdateMetodologiaDto updateMetodologiaDto) {
        MetodologiaEntity entity = metodologiaRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new EntityNotFoundException("Metodología no encontrada con ID: " + id));
        
        if (updateMetodologiaDto.getNombre() != null && !updateMetodologiaDto.getNombre().trim().isEmpty()) {
            entity.setNombre(updateMetodologiaDto.getNombre());
        }
        
        if (updateMetodologiaDto.getDescripcion() != null) {
            entity.setDescripcion(updateMetodologiaDto.getDescripcion());
        }
        
        MetodologiaEntity savedEntity = metodologiaRepository.save(entity);
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        MetodologiaEntity entity = metodologiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metodología no encontrada con ID: " + id));
        
        metodologiaRepository.delete(entity);
    }
    
    private MetodologiaResponseDto toResponseDto(MetodologiaEntity entity) {
        MetodologiaResponseDto dto = new MetodologiaResponseDto();
        dto.setId(entity.getIdMetodologia());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        return dto;
    }
}

