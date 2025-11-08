package com.icheha.aprendia_api.users.role.services;

import com.icheha.aprendia_api.users.role.data.dtos.CreateManyRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.CreateRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto;

import java.util.List;
import java.util.Optional;

public interface IRolePersonService {
    
    RolePersonResponseDto create(CreateRolePersonDto createRolePersonDto);
    
    List<RolePersonResponseDto> createMany(CreateManyRolePersonDto createManyRolePersonDto);
    
    Optional<RolePersonResponseDto> findById(Long id);
    
    List<RolePersonResponseDto> findByPersonId(Long personId);
}

