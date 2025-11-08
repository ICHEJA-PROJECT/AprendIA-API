package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.mappers.TownMapper;
import com.icheha.aprendia_api.users.person.data.repositories.TownRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Town;
import com.icheha.aprendia_api.users.person.domain.repositories.ITownRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TownRepositoryImpl implements ITownRepository {
    
    private final TownRepository townRepository;
    private final TownMapper townMapper;
    
    public TownRepositoryImpl(@Lazy TownRepository townRepository,
                              TownMapper townMapper) {
        this.townRepository = townRepository;
        this.townMapper = townMapper;
    }
    
    @Override
    public List<Town> findByMunicipality(Long municipalityId) {
        if (municipalityId == null) {
            return List.of();
        }
        return townRepository.findByMunicipalityId(municipalityId).stream()
                .map(townMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Town> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return townRepository.findById(id)
                .map(townMapper::toDomain);
    }
    
    @Override
    public List<Town> findAll() {
        // Usar consulta con JOIN FETCH para cargar relaciones de forma controlada
        return townRepository.findAllWithRelations().stream()
                .map(townMapper::toDomainWithoutMunicipality)
                .collect(Collectors.toList());
    }
    
    @Override
    public Town save(Town town) {
        if (town == null) {
            throw new IllegalArgumentException("Town no puede ser null");
        }
        com.icheha.aprendia_api.users.person.data.entities.TownEntity entity = townMapper.toEntity(town);
        com.icheha.aprendia_api.users.person.data.entities.TownEntity savedEntity = townRepository.save(entity);
        return townMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        townRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return townRepository.existsById(id);
    }
}

