package com.icheha.aprendia_api.exercises.topics.services.impl;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.CuadernilloResponseDto;
import com.icheha.aprendia_api.exercises.topics.data.entities.CuadernilloEntity;
import com.icheha.aprendia_api.exercises.topics.data.entities.LearningPath;
import com.icheha.aprendia_api.exercises.topics.data.repositories.CuadernilloRepository;
import com.icheha.aprendia_api.exercises.topics.data.repositories.LearningPathRepository;
import com.icheha.aprendia_api.exercises.topics.services.ICuadernilloService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuadernilloServiceImpl implements ICuadernilloService {
    
    private final CuadernilloRepository cuadernilloRepository;
    private final LearningPathRepository learningPathRepository;
    
    @Autowired
    public CuadernilloServiceImpl(CuadernilloRepository cuadernilloRepository,
                                  LearningPathRepository learningPathRepository) {
        this.cuadernilloRepository = cuadernilloRepository;
        this.learningPathRepository = learningPathRepository;
    }
    
    @Override
    @Transactional
    public CuadernilloResponseDto create(CreateCuadernilloDto createCuadernilloDto) {
        // Validar que la ruta de aprendizaje existe
        LearningPath rutaAprendizaje = learningPathRepository.findById(createCuadernilloDto.getIdRutaAprendizaje())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Ruta de aprendizaje no encontrada con ID: " + createCuadernilloDto.getIdRutaAprendizaje()));
        
        CuadernilloEntity entity = new CuadernilloEntity();
        entity.setIdRutaAprendizaje(createCuadernilloDto.getIdRutaAprendizaje());
        entity.setNombre(createCuadernilloDto.getNombre());
        entity.setDescripcion(createCuadernilloDto.getDescripcion());
        entity.setObjetivo(createCuadernilloDto.getObjetivo());
        entity.setUrlImagen(createCuadernilloDto.getUrlImagen());
        
        CuadernilloEntity savedEntity = cuadernilloRepository.save(entity);
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CuadernilloResponseDto> findAll() {
        return cuadernilloRepository.findAllWithRelations().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CuadernilloResponseDto> findById(Long id) {
        return cuadernilloRepository.findByIdWithRelations(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CuadernilloResponseDto> findByRutaAprendizaje(Long idRutaAprendizaje) {
        return cuadernilloRepository.findByRutaAprendizaje(idRutaAprendizaje).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CuadernilloResponseDto> findByNombreContaining(String nombre) {
        return cuadernilloRepository.findByNombreContaining(nombre).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public CuadernilloResponseDto update(Long id, UpdateCuadernilloDto updateCuadernilloDto) {
        CuadernilloEntity entity = cuadernilloRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + id));
        
        // Actualizar ruta de aprendizaje si se proporciona
        if (updateCuadernilloDto.getIdRutaAprendizaje() != null) {
            LearningPath rutaAprendizaje = learningPathRepository.findById(updateCuadernilloDto.getIdRutaAprendizaje())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Ruta de aprendizaje no encontrada con ID: " + updateCuadernilloDto.getIdRutaAprendizaje()));
            entity.setIdRutaAprendizaje(updateCuadernilloDto.getIdRutaAprendizaje());
        }
        
        // Actualizar campos opcionales
        if (updateCuadernilloDto.getNombre() != null && !updateCuadernilloDto.getNombre().trim().isEmpty()) {
            entity.setNombre(updateCuadernilloDto.getNombre());
        }
        
        if (updateCuadernilloDto.getDescripcion() != null) {
            entity.setDescripcion(updateCuadernilloDto.getDescripcion());
        }
        
        if (updateCuadernilloDto.getObjetivo() != null) {
            entity.setObjetivo(updateCuadernilloDto.getObjetivo());
        }
        
        if (updateCuadernilloDto.getUrlImagen() != null) {
            entity.setUrlImagen(updateCuadernilloDto.getUrlImagen());
        }
        
        CuadernilloEntity savedEntity = cuadernilloRepository.save(entity);
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        CuadernilloEntity entity = cuadernilloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cuadernillo no encontrado con ID: " + id));
        
        cuadernilloRepository.delete(entity);
    }
    
    private CuadernilloResponseDto toResponseDto(CuadernilloEntity entity) {
        CuadernilloResponseDto dto = new CuadernilloResponseDto();
        dto.setId(entity.getIdCuadernillo());
        dto.setIdRutaAprendizaje(entity.getIdRutaAprendizaje());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setObjetivo(entity.getObjetivo());
        dto.setUrlImagen(entity.getUrlImagen());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        return dto;
    }
}

