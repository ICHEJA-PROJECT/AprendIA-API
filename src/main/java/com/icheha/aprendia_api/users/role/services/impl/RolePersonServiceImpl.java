package com.icheha.aprendia_api.users.role.services.impl;

import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import com.icheha.aprendia_api.users.role.data.dtos.CreateManyRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.CreateRolePersonDto;
import com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto;
import com.icheha.aprendia_api.users.role.data.mappers.RolePersonMapper;
import com.icheha.aprendia_api.users.role.domain.repositories.IRolePersonRepository;
import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolePersonServiceImpl implements IRolePersonService {
    
    private final IRolePersonRepository rolePersonRepository;
    private final RolePersonMapper rolePersonMapper;
    
    public RolePersonServiceImpl(IRolePersonRepository rolePersonRepository,
                                RolePersonMapper rolePersonMapper) {
        this.rolePersonRepository = rolePersonRepository;
        this.rolePersonMapper = rolePersonMapper;
    }
    
    @Override
    @Transactional
    public RolePersonResponseDto create(CreateRolePersonDto createRolePersonDto) {
        PersonaRol personaRol = new PersonaRol.Builder()
                .idPersona(createRolePersonDto.getPersonId())
                .idRol(createRolePersonDto.getRoleId())
                .build();
        
        PersonaRol saved = rolePersonRepository.create(personaRol);
        return rolePersonMapper.toResponseDto(saved);
    }
    
    @Override
    @Transactional
    public List<RolePersonResponseDto> createMany(CreateManyRolePersonDto createManyRolePersonDto) {
        List<PersonaRol> personaRoles = createManyRolePersonDto.getRoleIds().stream()
                .map(roleId -> new PersonaRol.Builder()
                        .idPersona(createManyRolePersonDto.getPersonId())
                        .idRol(roleId)
                        .build())
                .collect(Collectors.toList());
        
        List<PersonaRol> saved = rolePersonRepository.createMany(personaRoles);
        return saved.stream()
                .map(rolePersonMapper::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<RolePersonResponseDto> findById(Long id) {
        return rolePersonRepository.findById(id)
                .map(rolePersonMapper::toResponseDto);
    }
    
    @Override
    public List<RolePersonResponseDto> findByPersonId(Long personId) {
        return rolePersonRepository.findByPersonId(personId).stream()
                .map(rolePersonMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}

