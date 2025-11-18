package com.icheha.aprendia_api.users.student.services.impl;

import com.icheha.aprendia_api.users.student.data.dtos.CreateRolParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.RolParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateRolParienteDto;
import com.icheha.aprendia_api.users.student.domain.entities.RolPariente;
import com.icheha.aprendia_api.users.student.domain.repositories.IRolParienteRepository;
import com.icheha.aprendia_api.users.student.services.IRolParienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolParienteServiceImpl implements IRolParienteService {
    
    private final IRolParienteRepository rolParienteRepository;
    
    public RolParienteServiceImpl(IRolParienteRepository rolParienteRepository) {
        this.rolParienteRepository = rolParienteRepository;
    }
    
    @Override
    @Transactional
    public RolParienteResponseDto create(CreateRolParienteDto createRolParienteDto) {
        // Verificar si ya existe un rol con ese nombre
        Optional<RolPariente> existing = rolParienteRepository.findByNombre(createRolParienteDto.getNombre());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol de pariente con el nombre: " + createRolParienteDto.getNombre());
        }
        
        RolPariente rolPariente = new RolPariente.Builder()
                .nombre(createRolParienteDto.getNombre())
                .build();
        
        RolPariente saved = rolParienteRepository.create(rolPariente);
        return toResponseDto(saved);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<RolParienteResponseDto> findAll() {
        return rolParienteRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<RolParienteResponseDto> findById(Long id) {
        return rolParienteRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public RolParienteResponseDto update(Long id, UpdateRolParienteDto updateRolParienteDto) {
        RolPariente existing = rolParienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol de pariente no encontrado con ID: " + id));
        
        // Verificar si el nuevo nombre ya existe en otro rol
        Optional<RolPariente> existingWithName = rolParienteRepository.findByNombre(updateRolParienteDto.getNombre());
        if (existingWithName.isPresent() && !existingWithName.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe un rol de pariente con el nombre: " + updateRolParienteDto.getNombre());
        }
        
        RolPariente updated = new RolPariente.Builder()
                .id(existing.getId())
                .nombre(updateRolParienteDto.getNombre())
                .build();
        
        RolPariente saved = rolParienteRepository.update(updated);
        return toResponseDto(saved);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        RolPariente existing = rolParienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol de pariente no encontrado con ID: " + id));
        
        rolParienteRepository.delete(id);
    }
    
    private RolParienteResponseDto toResponseDto(RolPariente rolPariente) {
        RolParienteResponseDto dto = new RolParienteResponseDto();
        dto.setId(rolPariente.getId());
        dto.setNombre(rolPariente.getNombre());
        return dto;
    }
}

