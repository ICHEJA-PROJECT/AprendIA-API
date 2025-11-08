package com.icheha.aprendia_api.users.role.services;

import com.icheha.aprendia_api.users.role.data.dtos.CreateRoleDto;
import com.icheha.aprendia_api.users.role.data.dtos.RoleResponseDto;
import com.icheha.aprendia_api.users.role.data.dtos.UpdateRoleDto;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    
    RoleResponseDto create(CreateRoleDto createRoleDto);
    
    List<RoleResponseDto> findAll();
    
    Optional<RoleResponseDto> findById(Long id);
    
    RoleResponseDto update(Long id, UpdateRoleDto updateRoleDto);
    
    void deleteById(Long id);
}

