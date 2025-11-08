package com.icheha.aprendia_api.users.student.data.repositories.impl;

import com.icheha.aprendia_api.users.student.data.entities.ProgenitorEntity;
import com.icheha.aprendia_api.users.student.data.mappers.ProgenitorMapper;
import com.icheha.aprendia_api.users.student.data.repositories.ProgenitorRepository;
import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;
import com.icheha.aprendia_api.users.student.domain.repositories.IProgenitorRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProgenitorRepositoryImpl implements IProgenitorRepository {
    
    private final ProgenitorRepository progenitorRepository;
    private final ProgenitorMapper progenitorMapper;
    
    public ProgenitorRepositoryImpl(@Lazy ProgenitorRepository progenitorRepository,
                                   ProgenitorMapper progenitorMapper) {
        this.progenitorRepository = progenitorRepository;
        this.progenitorMapper = progenitorMapper;
    }
    
    @Override
    public Progenitor create(Progenitor progenitor) {
        if (progenitor == null) {
            throw new IllegalArgumentException("Progenitor no puede ser nulo");
        }
        
        ProgenitorEntity entity = progenitorMapper.toEntity(progenitor);
        ProgenitorEntity savedEntity = progenitorRepository.save(entity);
        return progenitorMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Progenitor> findByCurp(String curp) {
        if (curp == null || curp.trim().isEmpty()) {
            return Optional.empty();
        }
        return progenitorRepository.findByCurp(curp)
                .map(progenitorMapper::toDomain);
    }
}

