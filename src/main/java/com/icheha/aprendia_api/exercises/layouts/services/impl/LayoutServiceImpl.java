package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.icheha.aprendia_api.exercises.layouts.data.dtos.request.CreateLayoutDto;
import com.icheha.aprendia_api.exercises.layouts.data.dtos.response.LayoutResponseDto;
import com.icheha.aprendia_api.exercises.layouts.data.entities.LayoutEntity;
import com.icheha.aprendia_api.exercises.layouts.data.repositories.LayoutRepository;
import com.icheha.aprendia_api.exercises.layouts.services.ILayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LayoutServiceImpl implements ILayoutService {
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Override
    public LayoutResponseDto createLayout(CreateLayoutDto createLayoutDto) {
        // Crear nueva entidad
        LayoutEntity entity = new LayoutEntity();
        entity.setNombre(createLayoutDto.getName());
        entity.setAtributos(createLayoutDto.getAttributes());
        // TODO: Implementar relación con TypeLayout cuando esté disponible
        // entity.setTypeLayoutId(createLayoutDto.getTypeLayoutId());
        
        // Guardar en la base de datos
        LayoutEntity savedEntity = layoutRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity, createLayoutDto.getTypeLayoutId());
    }
    
    @Override
    public List<LayoutResponseDto> getAllLayouts() {
        List<LayoutEntity> entities = layoutRepository.findAll();
        return entities.stream()
                .map(entity -> toResponseDto(entity, null))
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private LayoutResponseDto toResponseDto(LayoutEntity entity, Long typeLayoutId) {
        LayoutResponseDto dto = new LayoutResponseDto();
        dto.setId(entity.getIdLayout());
        dto.setName(entity.getNombre());
        dto.setAttributes(entity.getAtributos());
        dto.setTypeLayoutId(typeLayoutId);
        
        // Obtener nombre del tipo de layout si está disponible
        if (entity.getTipoLayout() != null) {
            dto.setTypeLayoutName(entity.getTipoLayout().getNombre());
        } else if (typeLayoutId != null) {
            dto.setTypeLayoutName("Type Layout " + typeLayoutId);
        }
        
        // Contar recursos y templates asociados
        dto.setResourceCount(entity.getRecursos() != null ? entity.getRecursos().size() : 0);
        dto.setTemplateCount(entity.getTemplates() != null ? entity.getTemplates().size() : 0);
        
        return dto;
    }
}
