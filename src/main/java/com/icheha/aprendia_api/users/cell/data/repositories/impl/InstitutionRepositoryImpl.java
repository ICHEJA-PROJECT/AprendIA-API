package com.icheha.aprendia_api.users.cell.data.repositories.impl;

import com.icheha.aprendia_api.users.cell.data.entities.InstitutionEntity;
import com.icheha.aprendia_api.users.cell.data.mappers.InstitutionMapper;
import com.icheha.aprendia_api.users.cell.data.repositories.InstitutionRepository;
import com.icheha.aprendia_api.users.cell.domain.entities.Institution;
import com.icheha.aprendia_api.users.cell.domain.repositories.IInstitutionRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InstitutionRepositoryImpl implements IInstitutionRepository {
    
    private final InstitutionRepository institutionRepository;
    private final InstitutionMapper institutionMapper;
    
    public InstitutionRepositoryImpl(@Lazy InstitutionRepository institutionRepository,
                                   InstitutionMapper institutionMapper) {
        this.institutionRepository = institutionRepository;
        this.institutionMapper = institutionMapper;
    }
    
    @Override
    public Institution create(Institution institution) {
        if (institution == null) {
            throw new IllegalArgumentException("Institution no puede ser nulo");
        }
        
        InstitutionEntity entity = institutionMapper.toEntity(institution);
        InstitutionEntity savedEntity = institutionRepository.save(entity);
        return institutionMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll().stream()
                .map(institutionMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Institution> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return institutionRepository.findById(id)
                .map(institutionMapper::toDomain);
    }
}

