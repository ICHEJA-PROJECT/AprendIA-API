package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.mappers.ZipcodeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.ZipcodeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;
import com.icheha.aprendia_api.users.person.domain.repositories.IZipcodeRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ZipcodeRepositoryImpl implements IZipcodeRepository {
    
    private final ZipcodeRepository zipcodeRepository;
    private final ZipcodeMapper zipcodeMapper;
    
    public ZipcodeRepositoryImpl(@Lazy ZipcodeRepository zipcodeRepository,
                                ZipcodeMapper zipcodeMapper) {
        this.zipcodeRepository = zipcodeRepository;
        this.zipcodeMapper = zipcodeMapper;
    }
    
    @Override
    public Zipcode save(Zipcode zipcode) {
        if (zipcode == null) {
            throw new IllegalArgumentException("Zipcode no puede ser nulo");
        }
        com.icheha.aprendia_api.users.person.data.entities.ZipcodeEntity entity = zipcodeMapper.toEntity(zipcode);
        com.icheha.aprendia_api.users.person.data.entities.ZipcodeEntity savedEntity = zipcodeRepository.save(entity);
        return zipcodeMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Zipcode> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return zipcodeRepository.findById(id)
                .map(zipcodeMapper::toDomain);
    }
    
    @Override
    public List<Zipcode> findAll() {
        // Usar consulta que no carga relaciones para evitar StackOverflowError
        return zipcodeRepository.findAllWithoutRelations().stream()
                .map(zipcodeMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        zipcodeRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return zipcodeRepository.existsById(id);
    }
}

