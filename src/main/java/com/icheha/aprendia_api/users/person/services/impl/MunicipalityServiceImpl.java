package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.MunicipalityResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.StateMapper;
import com.icheha.aprendia_api.users.person.data.repositories.StateRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import com.icheha.aprendia_api.users.person.domain.repositories.IMunicipalityRepository;
import com.icheha.aprendia_api.users.person.services.IMunicipalityService;
import com.icheha.aprendia_api.users.person.data.repositories.MunicipalityRepository;
import com.icheha.aprendia_api.users.person.data.mappers.MunicipalityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityServiceImpl implements IMunicipalityService {
    
    private final IMunicipalityRepository municipalityRepository;
    private final MunicipalityRepository municipalityJpaRepository;
    private final MunicipalityMapper municipalityMapper;
    private final StateRepository stateRepository;
    private final StateMapper stateMapper;
    
    public MunicipalityServiceImpl(IMunicipalityRepository municipalityRepository,
                                  MunicipalityRepository municipalityJpaRepository,
                                  MunicipalityMapper municipalityMapper,
                                  StateRepository stateRepository,
                                  StateMapper stateMapper) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityJpaRepository = municipalityJpaRepository;
        this.municipalityMapper = municipalityMapper;
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MunicipalityResponseDto> findByState(Long stateId) {
        return municipalityRepository.findByState(stateId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public MunicipalityResponseDto create(CreateMunicipalityDto createMunicipalityDto) {
        State state = stateMapper.toDomain(
            stateRepository.findById(createMunicipalityDto.getStateId())
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + createMunicipalityDto.getStateId()))
        );
        
        Municipality municipality = new Municipality.Builder()
                .nombre(createMunicipalityDto.getNombre())
                .estado(state)
                .build();
        
        Municipality savedMunicipality = municipalityRepository.save(municipality);
        return toResponseDto(savedMunicipality);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MunicipalityResponseDto findById(Long id) {
        Municipality municipality = municipalityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + id));
        return toResponseDto(municipality);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MunicipalityResponseDto> findAll() {
        return municipalityRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MunicipalityResponseDto> findAll(Pageable pageable) {
        return municipalityJpaRepository.findAll(pageable)
                .map(entity -> {
                    Municipality municipality = municipalityMapper.toDomain(entity);
                    return toResponseDto(municipality);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MunicipalityResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return municipalityJpaRepository.findAll(pageable)
                    .map(entity -> {
                        Municipality municipality = municipalityMapper.toDomain(entity);
                        return toResponseDto(municipality);
                    });
        }
        return municipalityJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    Municipality municipality = municipalityMapper.toDomain(entity);
                    return toResponseDto(municipality);
                });
    }
    
    @Override
    @Transactional
    public MunicipalityResponseDto update(Long id, UpdateMunicipalityDto updateMunicipalityDto) {
        Municipality existingMunicipality = municipalityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + id));
        
        Municipality.Builder builder = new Municipality.Builder()
                .id(existingMunicipality.getId())
                .nombre(updateMunicipalityDto.getNombre() != null ? updateMunicipalityDto.getNombre() : existingMunicipality.getNombre());
        
        if (updateMunicipalityDto.getStateId() != null) {
            State state = stateMapper.toDomain(
                stateRepository.findById(updateMunicipalityDto.getStateId())
                    .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + updateMunicipalityDto.getStateId()))
            );
            builder.estado(state);
        } else {
            builder.estado(existingMunicipality.getEstado());
        }
        
        Municipality updatedMunicipality = municipalityRepository.save(builder.build());
        return toResponseDto(updatedMunicipality);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!municipalityRepository.existsById(id)) {
            throw new EntityNotFoundException("Municipio no encontrado con ID: " + id);
        }
        municipalityRepository.deleteById(id);
    }
    
    private MunicipalityResponseDto toResponseDto(Municipality municipality) {
        MunicipalityResponseDto dto = new MunicipalityResponseDto();
        dto.setId(municipality.getId());
        dto.setNombre(municipality.getNombre());
        
        if (municipality.getEstado() != null) {
            dto.setStateId(municipality.getEstado().getId());
            dto.setStateName(municipality.getEstado().getNombre());
        }
        
        return dto;
    }
}

