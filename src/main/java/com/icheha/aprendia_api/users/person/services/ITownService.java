package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateTownDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.TownResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITownService {
    List<TownResponseDto> findByMunicipality(Long municipalityId);
    
    TownResponseDto create(CreateTownDto createTownDto);
    
    TownResponseDto findById(Long id);
    
    List<TownResponseDto> findAll();
    
    Page<TownResponseDto> findAll(Pageable pageable);
    
    Page<TownResponseDto> search(String search, Pageable pageable);
    
    TownResponseDto update(Long id, UpdateTownDto updateTownDto);
    
    void deleteById(Long id);
}

