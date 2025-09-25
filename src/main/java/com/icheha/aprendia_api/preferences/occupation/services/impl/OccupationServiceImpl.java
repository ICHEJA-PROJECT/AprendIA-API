package com.icheha.aprendia_api.preferences.occupation.services.impl;

import com.icheha.aprendia_api.preferences.occupation.data.dtos.request.CreateOccupationDto;
import com.icheha.aprendia_api.preferences.occupation.data.dtos.response.OccupationResponseDto;
import com.icheha.aprendia_api.preferences.occupation.domain.entities.Occupation;
import com.icheha.aprendia_api.preferences.occupation.domain.repositories.IOccupationRepository;
import com.icheha.aprendia_api.preferences.occupation.services.IOccupationService;
import com.icheha.aprendia_api.preferences.occupation.services.mappers.OccupationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de Occupation
 */
@Service
@Transactional
public class OccupationServiceImpl implements IOccupationService {
    
    private static final Logger logger = LoggerFactory.getLogger(OccupationServiceImpl.class);
    
    @Autowired
    private IOccupationRepository occupationRepository;
    
    @Autowired
    private OccupationMapper occupationMapper;
    
    @Override
    public OccupationResponseDto create(CreateOccupationDto createOccupationDto) {
        logger.debug("Creating occupation with name: {}", createOccupationDto.getName());
        
        // Verificar si ya existe una ocupación con ese nombre
        if (occupationRepository.existsByName(createOccupationDto.getName())) {
            throw new IllegalArgumentException("Ya existe una ocupación con el nombre: " + createOccupationDto.getName());
        }
        
        // Convertir DTO a entidad de dominio
        Occupation occupation = occupationMapper.toDomain(createOccupationDto);
        
        // Guardar en el repositorio
        Occupation savedOccupation = occupationRepository.save(occupation);
        
        logger.info("Occupation created successfully with ID: {}", savedOccupation.getId());
        
        return occupationMapper.toResponseDto(savedOccupation);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OccupationResponseDto> findAll() {
        logger.debug("Finding all occupations");
        
        List<Occupation> occupations = occupationRepository.findAll();
        
        logger.info("Found {} occupations", occupations.size());
        
        return occupationMapper.toResponseDtoList(occupations);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OccupationResponseDto findById(Long id) {
        logger.debug("Finding occupation by ID: {}", id);
        
        Optional<Occupation> occupationOpt = occupationRepository.findById(id);
        
        if (occupationOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró una ocupación con ID: " + id);
        }
        
        Occupation occupation = occupationOpt.get();
        logger.info("Occupation found: {}", occupation.getName());
        
        return occupationMapper.toResponseDto(occupation);
    }
    
    @Override
    @Transactional(readOnly = true)
    public OccupationResponseDto findByName(String name) {
        logger.debug("Finding occupation by name: {}", name);
        
        Optional<Occupation> occupationOpt = occupationRepository.findByName(name);
        
        if (occupationOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró una ocupación con nombre: " + name);
        }
        
        Occupation occupation = occupationOpt.get();
        logger.info("Occupation found: {}", occupation.getName());
        
        return occupationMapper.toResponseDto(occupation);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<OccupationResponseDto> findByNameContaining(String name) {
        logger.debug("Finding occupations containing name: {}", name);
        
        List<Occupation> occupations = occupationRepository.findByNameContaining(name);
        
        logger.info("Found {} occupations containing '{}'", occupations.size(), name);
        
        return occupationMapper.toResponseDtoList(occupations);
    }
    
    @Override
    public void deleteById(Long id) {
        logger.debug("Deleting occupation with ID: {}", id);
        
        if (!occupationRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró una ocupación con ID: " + id);
        }
        
        occupationRepository.deleteById(id);
        
        logger.info("Occupation deleted successfully with ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        logger.debug("Checking if occupation exists with name: {}", name);
        
        boolean exists = occupationRepository.existsByName(name);
        
        logger.debug("Occupation exists with name '{}': {}", name, exists);
        
        return exists;
    }
}
