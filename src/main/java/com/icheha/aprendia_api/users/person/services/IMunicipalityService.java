package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateMunicipalityDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.MunicipalityResponseDto;

import java.util.List;

public interface IMunicipalityService {
    List<MunicipalityResponseDto> findByState(Long stateId);
    
    MunicipalityResponseDto create(CreateMunicipalityDto createMunicipalityDto);
    
    MunicipalityResponseDto findById(Long id);
    
    List<MunicipalityResponseDto> findAll();
    
    MunicipalityResponseDto update(Long id, UpdateMunicipalityDto updateMunicipalityDto);
    
    void deleteById(Long id);
}

