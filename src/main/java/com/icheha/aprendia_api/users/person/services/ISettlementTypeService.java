package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementTypeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementTypeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISettlementTypeService {
    
    SettlementTypeResponseDto create(CreateSettlementTypeDto createSettlementTypeDto);
    
    SettlementTypeResponseDto findById(Long id);
    
    List<SettlementTypeResponseDto> findAll();
    
    Page<SettlementTypeResponseDto> findAll(Pageable pageable);
    
    Page<SettlementTypeResponseDto> search(String search, Pageable pageable);
    
    SettlementTypeResponseDto update(Long id, UpdateSettlementTypeDto updateSettlementTypeDto);
    
    void deleteById(Long id);
}

