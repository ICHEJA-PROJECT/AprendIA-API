package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.RoadTypeEntity;
import com.icheha.aprendia_api.users.person.data.mappers.RoadTypeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.RoadTypeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.RoadType;
import com.icheha.aprendia_api.users.person.domain.repositories.IRoadTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("roadTypeRepositoryImpl")
public class RoadTypeRepositoryImpl implements IRoadTypeRepository {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final RoadTypeRepository roadTypeRepository;
    private final RoadTypeMapper roadTypeMapper;
    
    public RoadTypeRepositoryImpl(@Lazy RoadTypeRepository roadTypeRepository, 
                                  RoadTypeMapper roadTypeMapper) {
        this.roadTypeRepository = roadTypeRepository;
        this.roadTypeMapper = roadTypeMapper;
    }
    
    @Override
    public List<RoadType> findAll() {
        // Usar EntityManager directamente para evitar problemas de proxy circular
        List<RoadTypeEntity> entities = entityManager.createQuery(
                "SELECT r FROM RoadTypeEntity r", RoadTypeEntity.class)
                .getResultList();
        return entities.stream()
                .map(entity -> roadTypeMapper.toDomain(entity))
                .filter(domain -> domain != null)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<RoadType> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return roadTypeRepository.findById(id)
                .map(entity -> roadTypeMapper.toDomain(entity));
    }
    
    @Override
    public RoadType save(RoadType roadType) {
        if (roadType == null) {
            throw new IllegalArgumentException("RoadType no puede ser null");
        }
        RoadTypeEntity entity = roadTypeMapper.toEntity(roadType);
        RoadTypeEntity savedEntity = roadTypeRepository.save(entity);
        return roadTypeMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        roadTypeRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return roadTypeRepository.existsById(id);
    }
}

