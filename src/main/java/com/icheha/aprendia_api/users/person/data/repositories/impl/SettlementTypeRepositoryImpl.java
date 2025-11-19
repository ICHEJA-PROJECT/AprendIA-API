package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.mappers.SettlementTypeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.SettlementTypeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;
import com.icheha.aprendia_api.users.person.domain.repositories.ISettlementTypeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SettlementTypeRepositoryImpl implements ISettlementTypeRepository {
    
    private final SettlementTypeRepository settlementTypeRepository;
    private final SettlementTypeMapper settlementTypeMapper;
    
    public SettlementTypeRepositoryImpl(@Lazy SettlementTypeRepository settlementTypeRepository,
                                       SettlementTypeMapper settlementTypeMapper) {
        this.settlementTypeRepository = settlementTypeRepository;
        this.settlementTypeMapper = settlementTypeMapper;
    }
    
    @Override
    public SettlementType save(SettlementType settlementType) {
        if (settlementType == null) {
            throw new IllegalArgumentException("SettlementType no puede ser nulo");
        }
        com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity entity = settlementTypeMapper.toEntity(settlementType);
        com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity savedEntity = settlementTypeRepository.save(entity);
        return settlementTypeMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<SettlementType> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return settlementTypeRepository.findById(id)
                .map(settlementTypeMapper::toDomain);
    }
    
    @Override
    public List<SettlementType> findAll() {
        // Usar consulta que no carga relaciones para evitar StackOverflowError
        return settlementTypeRepository.findAllWithoutRelations().stream()
                .map(settlementTypeMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        settlementTypeRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return settlementTypeRepository.existsById(id);
    }
}

