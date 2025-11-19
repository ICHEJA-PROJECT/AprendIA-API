package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.ZipcodeResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.ZipcodeMapper;
import com.icheha.aprendia_api.users.person.data.repositories.ZipcodeRepository;
import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;
import com.icheha.aprendia_api.users.person.domain.repositories.IZipcodeRepository;
import com.icheha.aprendia_api.users.person.services.IZipcodeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZipcodeServiceImpl implements IZipcodeService {
    
    private final IZipcodeRepository zipcodeRepository;
    private final ZipcodeRepository zipcodeJpaRepository;
    private final ZipcodeMapper zipcodeMapper;
    
    public ZipcodeServiceImpl(IZipcodeRepository zipcodeRepository,
                             ZipcodeRepository zipcodeJpaRepository,
                             ZipcodeMapper zipcodeMapper) {
        this.zipcodeRepository = zipcodeRepository;
        this.zipcodeJpaRepository = zipcodeJpaRepository;
        this.zipcodeMapper = zipcodeMapper;
    }
    
    @Override
    @Transactional
    public ZipcodeResponseDto create(CreateZipcodeDto createZipcodeDto) {
        Zipcode zipcode = new Zipcode.Builder()
                .codigo(createZipcodeDto.getCodigo())
                .build();
        
        Zipcode savedZipcode = zipcodeRepository.save(zipcode);
        return toResponseDto(savedZipcode);
    }
    
    @Override
    @Transactional(readOnly = true)
    public ZipcodeResponseDto findById(Long id) {
        Zipcode zipcode = zipcodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Código postal no encontrado con ID: " + id));
        return toResponseDto(zipcode);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ZipcodeResponseDto> findAll() {
        return zipcodeRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ZipcodeResponseDto> findAll(Pageable pageable) {
        return zipcodeJpaRepository.findAll(pageable)
                .map(entity -> {
                    Zipcode zipcode = zipcodeMapper.toDomain(entity);
                    return toResponseDto(zipcode);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<ZipcodeResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return zipcodeJpaRepository.findAll(pageable)
                    .map(entity -> {
                        Zipcode zipcode = zipcodeMapper.toDomain(entity);
                        return toResponseDto(zipcode);
                    });
        }
        return zipcodeJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    Zipcode zipcode = zipcodeMapper.toDomain(entity);
                    return toResponseDto(zipcode);
                });
    }
    
    @Override
    @Transactional
    public ZipcodeResponseDto update(Long id, UpdateZipcodeDto updateZipcodeDto) {
        Zipcode existingZipcode = zipcodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Código postal no encontrado con ID: " + id));
        
        Zipcode updatedZipcode = new Zipcode.Builder()
                .id(existingZipcode.getId())
                .codigo(updateZipcodeDto.getCodigo() != null ? updateZipcodeDto.getCodigo() : existingZipcode.getCodigo())
                .build();
        
        Zipcode savedZipcode = zipcodeRepository.save(updatedZipcode);
        return toResponseDto(savedZipcode);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!zipcodeRepository.existsById(id)) {
            throw new EntityNotFoundException("Código postal no encontrado con ID: " + id);
        }
        zipcodeRepository.deleteById(id);
    }
    
    private ZipcodeResponseDto toResponseDto(Zipcode zipcode) {
        ZipcodeResponseDto dto = new ZipcodeResponseDto();
        dto.setId(zipcode.getId());
        dto.setCodigo(zipcode.getCodigo());
        return dto;
    }
}

