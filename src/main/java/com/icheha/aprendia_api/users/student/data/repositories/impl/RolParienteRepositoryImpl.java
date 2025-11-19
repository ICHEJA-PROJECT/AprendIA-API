package com.icheha.aprendia_api.users.student.data.repositories.impl;

import com.icheha.aprendia_api.users.student.data.entities.RolParienteEntity;
import com.icheha.aprendia_api.users.student.data.mappers.RolParienteMapper;
import com.icheha.aprendia_api.users.student.data.repositories.RolParienteRepository;
import com.icheha.aprendia_api.users.student.domain.entities.RolPariente;
import com.icheha.aprendia_api.users.student.domain.repositories.IRolParienteRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RolParienteRepositoryImpl implements IRolParienteRepository {
    
    private final RolParienteRepository rolParienteRepository;
    private final RolParienteMapper rolParienteMapper;
    
    public RolParienteRepositoryImpl(@Lazy RolParienteRepository rolParienteRepository,
                                    RolParienteMapper rolParienteMapper) {
        this.rolParienteRepository = rolParienteRepository;
        this.rolParienteMapper = rolParienteMapper;
    }
    
    @Override
    public RolPariente create(RolPariente rolPariente) {
        if (rolPariente == null) {
            throw new IllegalArgumentException("RolPariente no puede ser nulo");
        }
        
        RolParienteEntity entity = rolParienteMapper.toEntity(rolPariente);
        RolParienteEntity savedEntity = rolParienteRepository.save(entity);
        return rolParienteMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<RolPariente> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return rolParienteRepository.findById(id)
                .map(rolParienteMapper::toDomain);
    }
    
    @Override
    public Optional<RolPariente> findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return Optional.empty();
        }
        return rolParienteRepository.findByNombre(nombre)
                .map(rolParienteMapper::toDomain);
    }
    
    @Override
    public List<RolPariente> findAll() {
        return rolParienteRepository.findAllWithoutRelations().stream()
                .map(rolParienteMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public RolPariente update(RolPariente rolPariente) {
        if (rolPariente == null || rolPariente.getId() == null) {
            throw new IllegalArgumentException("RolPariente y su ID no pueden ser nulos");
        }
        
        RolParienteEntity entity = rolParienteRepository.findById(rolPariente.getId())
                .orElseThrow(() -> new IllegalArgumentException("RolPariente no encontrado con ID: " + rolPariente.getId()));
        
        entity.setNombre(rolPariente.getNombre());
        RolParienteEntity savedEntity = rolParienteRepository.save(entity);
        return rolParienteMapper.toDomain(savedEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        rolParienteRepository.deleteById(id);
    }
}

