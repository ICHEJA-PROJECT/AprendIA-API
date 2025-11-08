package com.icheha.aprendia_api.users.role.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.data.repositories.RolRepository;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.users.role.data.mappers.RoleMapper;
import com.icheha.aprendia_api.users.role.domain.repositories.IRoleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements IRoleRepository {
    
    private final RolRepository rolRepository;
    private final RoleMapper roleMapper;
    
    public RoleRepositoryImpl(@Lazy RolRepository rolRepository, RoleMapper roleMapper) {
        this.rolRepository = rolRepository;
        this.roleMapper = roleMapper;
    }
    
    @Override
    public Rol create(Rol rol) {
        if (rol == null) {
            throw new IllegalArgumentException("Rol no puede ser nulo");
        }
        
        RolEntity entity = roleMapper.toEntity(rol);
        RolEntity savedEntity = rolRepository.save(entity);
        return roleMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll().stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Rol> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return rolRepository.findById(id)
                .map(roleMapper::toDomain);
    }
    
    @Override
    public Rol update(Rol rol) {
        if (rol == null || rol.getIdRol() == null) {
            throw new IllegalArgumentException("Rol y su ID no pueden ser nulos");
        }
        
        RolEntity existingEntity = rolRepository.findById(rol.getIdRol())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + rol.getIdRol()));
        
        RolEntity updatedEntity = roleMapper.toEntity(rol);
        updatedEntity.setIdRol(existingEntity.getIdRol());
        // Mantener la descripción existente si no se proporciona una nueva
        if (updatedEntity.getDescripcion() == null) {
            updatedEntity.setDescripcion(existingEntity.getDescripcion());
        }
        
        RolEntity savedEntity = rolRepository.save(updatedEntity);
        return roleMapper.toDomain(savedEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!rolRepository.existsById(id)) {
            throw new IllegalArgumentException("Rol no encontrado con ID: " + id);
        }
        rolRepository.deleteById(id);
    }
}

