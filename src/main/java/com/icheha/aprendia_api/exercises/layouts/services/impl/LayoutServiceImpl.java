package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.UpdateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.TypeLayoutRepository;
import com.icheha.aprendia_api.exercises.layouts.services.ILayoutService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LayoutServiceImpl implements ILayoutService {
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Autowired
    private TypeLayoutRepository typeLayoutRepository;
    
    @Override
    @Transactional
    public LayoutResponseDto createLayout(CreateLayoutDto createLayoutDto) {
        // Validar que el tipo de layout existe
        typeLayoutRepository.findById(createLayoutDto.getTypeLayoutId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de layout no encontrado con ID: " + createLayoutDto.getTypeLayoutId()));
        
        // Crear nueva entidad
        LayoutEntity entity = new LayoutEntity();
        entity.setNombre(createLayoutDto.getName());
        entity.setDescripcion(createLayoutDto.getAttributes());
        entity.setIdTipoLayout(createLayoutDto.getTypeLayoutId());
        
        // Guardar en la base de datos
        LayoutEntity savedEntity = layoutRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity, createLayoutDto.getTypeLayoutId());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LayoutResponseDto> getAllLayouts() {
        List<LayoutEntity> entities = layoutRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, entity.getIdTipoLayout()))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<LayoutResponseDto> findById(Long id) {
        return layoutRepository.findById(id)
                .map(entity -> toResponseDto(entity, entity.getIdTipoLayout()));
    }
    
    @Override
    @Transactional
    public LayoutResponseDto update(Long id, UpdateLayoutDto updateLayoutDto) {
        LayoutEntity entity = layoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + id));
        
        if (updateLayoutDto.getName() != null && !updateLayoutDto.getName().trim().isEmpty()) {
            entity.setNombre(updateLayoutDto.getName());
        }
        
        if (updateLayoutDto.getAttributes() != null) {
            entity.setDescripcion(updateLayoutDto.getAttributes());
        }
        
        if (updateLayoutDto.getTypeLayoutId() != null) {
            typeLayoutRepository.findById(updateLayoutDto.getTypeLayoutId())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de layout no encontrado con ID: " + updateLayoutDto.getTypeLayoutId()));
            entity.setIdTipoLayout(updateLayoutDto.getTypeLayoutId());
        }
        
        LayoutEntity updatedEntity = layoutRepository.save(entity);
        return toResponseDto(updatedEntity, updatedEntity.getIdTipoLayout());
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        LayoutEntity entity = layoutRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Layout no encontrado con ID: " + id));
        layoutRepository.delete(entity);
    }
    
    // Método helper para convertir entidad a DTO
    private LayoutResponseDto toResponseDto(LayoutEntity entity, Long typeLayoutId) {
        LayoutResponseDto dto = new LayoutResponseDto();
        dto.setId(entity.getIdLayout());
        dto.setName(entity.getNombre());
        dto.setAttributes(entity.getDescripcion()); // Usar descripcion
        dto.setTypeLayoutId(typeLayoutId);
        
        // Contar recursos y templates asociados
        dto.setResourceCount(entity.getRecursos() != null ? entity.getRecursos().size() : 0);
        dto.setTemplateCount(entity.getTemplates() != null ? entity.getTemplates().size() : 0);
        
        return dto;
    }
}
