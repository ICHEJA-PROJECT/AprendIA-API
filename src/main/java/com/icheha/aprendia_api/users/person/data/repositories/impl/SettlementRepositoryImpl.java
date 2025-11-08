package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.SettlementEntity;
import com.icheha.aprendia_api.users.person.data.mappers.SettlementMapper;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Settlement;
import com.icheha.aprendia_api.users.person.domain.repositories.ISettlementRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SettlementRepositoryImpl implements ISettlementRepository {
    
    private final SettlementRepository settlementRepository;
    private final SettlementMapper settlementMapper;
    
    public SettlementRepositoryImpl(@Lazy SettlementRepository settlementRepository,
                                    SettlementMapper settlementMapper) {
        this.settlementRepository = settlementRepository;
        this.settlementMapper = settlementMapper;
    }
    
    @Override
    public List<Settlement> findByZipcode(String zipcode) {
        if (zipcode == null || zipcode.trim().isEmpty()) {
            return List.of();
        }
        return settlementRepository.findByZipcode(zipcode).stream()
                .map(settlementMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Settlement> findByMunicipalityAndTown(Long municipalityId, Long townId) {
        if (municipalityId == null) {
            return List.of();
        }
        if (townId != null) {
            return settlementRepository.findByMunicipalityIdAndTownId(municipalityId, townId).stream()
                    .map(settlementMapper::toDomain)
                    .collect(Collectors.toList());
        } else {
            return findByMunicipality(municipalityId);
        }
    }
    
    @Override
    public List<Settlement> findByMunicipality(Long municipalityId) {
        if (municipalityId == null) {
            return List.of();
        }
        return settlementRepository.findByMunicipalityId(municipalityId).stream()
                .map(settlementMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Settlement> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return settlementRepository.findById(id)
                .map(settlementMapper::toDomain);
    }
    
    @Override
    public List<Settlement> findAll() {
        // Usar consulta con JOIN FETCH para cargar relaciones de forma controlada
        return settlementRepository.findAllWithRelations().stream()
                .map(settlementMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Settlement save(Settlement settlement) {
        if (settlement == null) {
            throw new IllegalArgumentException("Settlement no puede ser null");
        }
        SettlementEntity entity = settlementMapper.toEntity(settlement);
        SettlementEntity savedEntity = settlementRepository.save(entity);
        return settlementMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        settlementRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return settlementRepository.existsById(id);
    }
}

