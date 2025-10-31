package com.icheha.aprendia_api.preferences.occupation.data.repositories;

import com.icheha.aprendia_api.preferences.occupation.data.entities.OccupationEntity;
import com.icheha.aprendia_api.preferences.occupation.domain.entities.Occupation;
import com.icheha.aprendia_api.preferences.occupation.domain.repositories.IOccupationRepository;
import com.icheha.aprendia_api.preferences.occupation.services.mappers.OccupationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio de dominio para Occupation
 * Conecta la capa de dominio con la capa de datos
 */
@Repository
public class OccupationRepositoryImpl implements IOccupationRepository {
    
    @Autowired
    @Lazy
    private OccupationRepository occupationRepository;
    
    @Autowired
    private OccupationMapper occupationMapper;
    
    @Override
    public Occupation save(Occupation occupation) {
        OccupationEntity entity = occupationMapper.toEntity(occupation);
        OccupationEntity savedEntity = occupationRepository.save(entity);
        return occupationMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Occupation> findById(Long id) {
        Optional<OccupationEntity> entityOpt = occupationRepository.findById(id);
        return entityOpt.map(occupationMapper::toDomain);
    }
    
    @Override
    public Optional<Occupation> findByName(String name) {
        Optional<OccupationEntity> entityOpt = occupationRepository.findByName(name);
        return entityOpt.map(occupationMapper::toDomain);
    }
    
    @Override
    public List<Occupation> findAll() {
        List<OccupationEntity> entities = occupationRepository.findAll();
        return entities.stream()
                .map(occupationMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Occupation> findByNameContaining(String name) {
        List<OccupationEntity> entities = occupationRepository.findByNameContaining(name);
        return entities.stream()
                .map(occupationMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public boolean existsByName(String name) {
        return occupationRepository.existsByName(name);
    }
    
    @Override
    public void deleteById(Long id) {
        occupationRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return occupationRepository.existsById(id);
    }
}

