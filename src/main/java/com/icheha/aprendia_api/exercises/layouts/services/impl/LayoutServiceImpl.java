package com.icheha.aprendia_api.exercises.layouts.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LayoutServiceImpl implements ILayoutService {
    
    @Autowired
    private LayoutRepository layoutRepository;
    
    @Autowired
    private TypeLayoutRepository typeLayoutRepository;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    @Transactional
    public LayoutResponseDto createLayout(CreateLayoutDto createLayoutDto) {
        // Validar que el tipo de layout existe
        typeLayoutRepository.findById(createLayoutDto.getTypeLayoutId())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de layout no encontrado con ID: " + createLayoutDto.getTypeLayoutId()));
        
        // Crear nueva entidad
        LayoutEntity entity = new LayoutEntity();
        entity.setNombre(createLayoutDto.getName());
        entity.setDescripcion(createLayoutDto.getDescription() != null ? createLayoutDto.getDescription() : createLayoutDto.getAttributes());
        
        // Parsear atributos desde String a Map si es necesario
        if (createLayoutDto.getAttributes() != null && !createLayoutDto.getAttributes().trim().isEmpty()) {
            try {
                // Intentar parsear como JSON
                Map<String, Object> atributosMap = objectMapper.readValue(createLayoutDto.getAttributes(), new TypeReference<Map<String, Object>>() {});
                entity.setAtributos(atributosMap);
            } catch (Exception e) {
                // Si falla, crear un mapa simple con el string
                Map<String, Object> atributosMap = new HashMap<>();
                atributosMap.put("attributes", createLayoutDto.getAttributes());
                entity.setAtributos(atributosMap);
            }
        }
        
        entity.setUrlImage(createLayoutDto.getUrlImage());
        entity.setIsActive(createLayoutDto.getIsActive() != null ? createLayoutDto.getIsActive() : true);
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
        
        if (updateLayoutDto.getDescription() != null) {
            entity.setDescripcion(updateLayoutDto.getDescription());
        }
        
        if (updateLayoutDto.getAttributes() != null) {
            try {
                // Intentar parsear como JSON
                Map<String, Object> atributosMap = objectMapper.readValue(updateLayoutDto.getAttributes(), new TypeReference<Map<String, Object>>() {});
                entity.setAtributos(atributosMap);
            } catch (Exception e) {
                // Si falla, crear un mapa simple con el string
                Map<String, Object> atributosMap = new HashMap<>();
                atributosMap.put("attributes", updateLayoutDto.getAttributes());
                entity.setAtributos(atributosMap);
            }
        }
        
        if (updateLayoutDto.getUrlImage() != null) {
            entity.setUrlImage(updateLayoutDto.getUrlImage());
        }
        
        if (updateLayoutDto.getIsActive() != null) {
            entity.setIsActive(updateLayoutDto.getIsActive());
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
        dto.setDescription(entity.getDescripcion());
        
        // Convertir atributos de Map a String JSON
        if (entity.getAtributos() != null) {
            try {
                dto.setAttributes(objectMapper.writeValueAsString(entity.getAtributos()));
            } catch (Exception e) {
                dto.setAttributes(entity.getAtributos().toString());
            }
        } else if (entity.getDescripcion() != null) {
            dto.setAttributes(entity.getDescripcion());
        } else {
            dto.setAttributes("{}");
        }
        
        dto.setTypeLayoutId(typeLayoutId);
        dto.setUrlImage(entity.getUrlImage());
        dto.setIsActive(entity.getIsActive() != null ? entity.getIsActive() : true);
        
        // Obtener nombre del tipo de layout si está disponible
        if (entity.getTipoLayout() != null) {
            dto.setTypeLayoutName(entity.getTipoLayout().getNombre());
        }
        
        // Contar recursos y templates asociados
        dto.setResourceCount(entity.getRecursos() != null ? entity.getRecursos().size() : 0);
        dto.setTemplateCount(entity.getTemplates() != null ? entity.getTemplates().size() : 0);
        
        return dto;
    }
}
