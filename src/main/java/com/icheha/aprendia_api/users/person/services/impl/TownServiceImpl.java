package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.TownResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.MunicipalityMapper;
import com.icheha.aprendia_api.users.person.data.repositories.MunicipalityRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Municipality;
import com.icheha.aprendia_api.users.person.domain.entities.Town;
import com.icheha.aprendia_api.users.person.domain.repositories.ITownRepository;
import com.icheha.aprendia_api.users.person.services.ITownService;
import com.icheha.aprendia_api.users.person.data.repositories.TownRepository;
import com.icheha.aprendia_api.users.person.data.mappers.TownMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements ITownService {
    
    private final ITownRepository townRepository;
    private final TownRepository townJpaRepository;
    private final TownMapper townMapper;
    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityMapper municipalityMapper;
    
    public TownServiceImpl(ITownRepository townRepository,
                           TownRepository townJpaRepository,
                           TownMapper townMapper,
                           MunicipalityRepository municipalityRepository,
                           MunicipalityMapper municipalityMapper) {
        this.townRepository = townRepository;
        this.townJpaRepository = townJpaRepository;
        this.townMapper = townMapper;
        this.municipalityRepository = municipalityRepository;
        this.municipalityMapper = municipalityMapper;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TownResponseDto> findByMunicipality(Long municipalityId) {
        return townRepository.findByMunicipality(municipalityId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public TownResponseDto create(CreateTownDto createTownDto) {
        Municipality municipality = municipalityMapper.toDomain(
            municipalityRepository.findById(createTownDto.getMunicipalityId())
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + createTownDto.getMunicipalityId()))
        );
        
        Town town = new Town.Builder()
                .nombre(createTownDto.getNombre())
                .municipio(municipality)
                .build();
        
        Town savedTown = townRepository.save(town);
        return toResponseDto(savedTown);
    }
    
    @Override
    @Transactional(readOnly = true)
    public TownResponseDto findById(Long id) {
        Town town = townRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con ID: " + id));
        return toResponseDto(town);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<TownResponseDto> findAll() {
        return townRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<TownResponseDto> findAll(Pageable pageable) {
        return townJpaRepository.findAll(pageable)
                .map(entity -> {
                    Town town = townMapper.toDomain(entity);
                    return toResponseDto(town);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<TownResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return townJpaRepository.findAll(pageable)
                    .map(entity -> {
                        Town town = townMapper.toDomain(entity);
                        return toResponseDto(town);
                    });
        }
        return townJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    Town town = townMapper.toDomain(entity);
                    return toResponseDto(town);
                });
    }
    
    @Override
    @Transactional
    public TownResponseDto update(Long id, UpdateTownDto updateTownDto) {
        Town existingTown = townRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada con ID: " + id));
        
        Town.Builder builder = new Town.Builder()
                .id(existingTown.getId())
                .nombre(updateTownDto.getNombre() != null ? updateTownDto.getNombre() : existingTown.getNombre());
        
        if (updateTownDto.getMunicipalityId() != null) {
            Municipality municipality = municipalityMapper.toDomain(
                municipalityRepository.findById(updateTownDto.getMunicipalityId())
                    .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado con ID: " + updateTownDto.getMunicipalityId()))
            );
            builder.municipio(municipality);
        } else {
            builder.municipio(existingTown.getMunicipio());
        }
        
        Town updatedTown = townRepository.save(builder.build());
        return toResponseDto(updatedTown);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!townRepository.existsById(id)) {
            throw new EntityNotFoundException("Ciudad no encontrada con ID: " + id);
        }
        townRepository.deleteById(id);
    }
    
    private TownResponseDto toResponseDto(Town town) {
        TownResponseDto dto = new TownResponseDto();
        dto.setId(town.getId());
        dto.setNombre(town.getNombre());
        
        if (town.getMunicipio() != null) {
            dto.setMunicipalityId(town.getMunicipio().getId());
            dto.setMunicipalityName(town.getMunicipio().getNombre());
        }
        
        return dto;
    }
}

