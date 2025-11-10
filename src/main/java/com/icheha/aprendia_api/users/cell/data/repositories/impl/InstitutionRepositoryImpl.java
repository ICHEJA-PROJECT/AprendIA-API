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
    
    @Override
    public Institution update(Institution institution) {
        if (institution == null || institution.getId() == null) {
            throw new IllegalArgumentException("Institution y su ID no pueden ser nulos");
        }
        
        InstitutionEntity entity = institutionRepository.findById(institution.getId())
                .orElseThrow(() -> new IllegalArgumentException("Institución no encontrada con ID: " + institution.getId()));
        
        entity.setRfc(institution.getRfc());
        entity.setRct(institution.getRct());
        entity.setName(institution.getName());
        
        InstitutionEntity updatedEntity = institutionRepository.save(entity);
        return institutionMapper.toDomain(updatedEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!institutionRepository.existsById(id)) {
            throw new IllegalArgumentException("Institución no encontrada con ID: " + id);
        }
        institutionRepository.deleteById(id);
    }
}

