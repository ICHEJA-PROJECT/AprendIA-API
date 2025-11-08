package com.icheha.aprendia_api.users.role.services.impl;

import com.icheha.aprendia_api.auth.data.entities.RolEntity;
import com.icheha.aprendia_api.auth.data.repositories.RolRepository;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import com.icheha.aprendia_api.users.role.data.dtos.CreateRoleDto;
import com.icheha.aprendia_api.users.role.data.dtos.RoleResponseDto;
import com.icheha.aprendia_api.users.role.data.dtos.UpdateRoleDto;
import com.icheha.aprendia_api.users.role.data.mappers.RoleMapper;
import com.icheha.aprendia_api.users.role.domain.repositories.IRoleRepository;
import com.icheha.aprendia_api.users.role.services.IRoleService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {
    
    private final IRoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final RolRepository rolRepository;
    
    public RoleServiceImpl(IRoleRepository roleRepository, 
                          RoleMapper roleMapper,
                          @Lazy RolRepository rolRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.rolRepository = rolRepository;
    }
    
    @Override
    @Transactional
    public RoleResponseDto create(CreateRoleDto createRoleDto) {
        Rol rol = new Rol.Builder()
                .nombre(createRoleDto.getName())
                .build();
        
        Rol savedRol = roleRepository.create(rol);
        // Obtener la entidad JPA para incluir la descripción en la respuesta
        RolEntity savedEntity = rolRepository.findById(savedRol.getIdRol())
                .orElseThrow(() -> new RuntimeException("Error al obtener el rol creado"));
        return roleMapper.toResponseDto(savedEntity);
    }
    
    @Override
    public List<RoleResponseDto> findAll() {
        return rolRepository.findAll().stream()
                .map(roleMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<RoleResponseDto> findById(Long id) {
        return rolRepository.findById(id)
                .map(roleMapper::toResponseDto);
    }
    
    @Override
    @Transactional
    public RoleResponseDto update(Long id, UpdateRoleDto updateRoleDto) {
        RolEntity existingEntity = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
        
        // Actualizar nombre si se proporciona
        if (updateRoleDto.getName() != null && !updateRoleDto.getName().isEmpty()) {
            existingEntity.setNombre(updateRoleDto.getName());
        }
        
        // Actualizar descripción si se proporciona
        if (updateRoleDto.getDescription() != null) {
            existingEntity.setDescripcion(updateRoleDto.getDescription());
        }
        
        RolEntity savedEntity = rolRepository.save(existingEntity);
        return roleMapper.toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!roleRepository.findById(id).isPresent()) {
            throw new RuntimeException("Rol no encontrado con ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}

