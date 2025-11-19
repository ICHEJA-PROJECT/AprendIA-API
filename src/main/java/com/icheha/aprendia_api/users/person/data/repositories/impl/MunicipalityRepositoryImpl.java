package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.mappers.MunicipalityMapper;
import com.icheha.aprendia_api.users.person.data.repositories.MunicipalityRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.repositories.IMunicipalityRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MunicipalityRepositoryImpl implements IMunicipalityRepository {
    
    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityMapper municipalityMapper;
    
    public MunicipalityRepositoryImpl(@Lazy MunicipalityRepository municipalityRepository,
                                      MunicipalityMapper municipalityMapper) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityMapper = municipalityMapper;
    }
    
    @Override
    public List<Municipality> findByState(Long stateId) {
        if (stateId == null) {
            return List.of();
        }
        return municipalityRepository.findByStateId(stateId).stream()
                .map(municipalityMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Municipality> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return municipalityRepository.findById(id)
                .map(municipalityMapper::toDomain);
    }
    
    @Override
    public List<Municipality> findAll() {
        // Usar consulta con JOIN FETCH para cargar relaciones de forma controlada
        return municipalityRepository.findAllWithRelations().stream()
                .map(municipalityMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Municipality save(Municipality municipality) {
        if (municipality == null) {
            throw new IllegalArgumentException("Municipality no puede ser null");
        }
        com.icheha.aprendia_api.users.person.data.entities.MunicipalityEntity entity = municipalityMapper.toEntity(municipality);
        com.icheha.aprendia_api.users.person.data.entities.MunicipalityEntity savedEntity = municipalityRepository.save(entity);
        return municipalityMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        municipalityRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return municipalityRepository.existsById(id);
    }
}

