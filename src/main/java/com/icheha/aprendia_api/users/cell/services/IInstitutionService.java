package com.icheha.aprendia_api.users.cell.services;

import com.icheha.aprendia_api.users.cell.data.dtos.CreateInstitutionDto;
import com.icheha.aprendia_api.users.cell.data.dtos.InstitutionResponseDto;

import java.util.List;
import java.util.Optional;

public interface IInstitutionService {
    
    InstitutionResponseDto create(CreateInstitutionDto createInstitutionDto);
    
    List<InstitutionResponseDto> findAll();
    
    Optional<InstitutionResponseDto> findById(Long id);
}

