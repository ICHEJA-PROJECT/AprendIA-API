package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateSettlementDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.SettlementResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISettlementService {
    List<SettlementResponseDto> findByZipcode(String zipcode);
    List<SettlementResponseDto> findByMunicipalityAndTown(Long municipalityId, Long townId);
    
    SettlementResponseDto create(CreateSettlementDto createSettlementDto);
    
    SettlementResponseDto findById(Long id);
    
    List<SettlementResponseDto> findAll();
    
    Page<SettlementResponseDto> findAll(Pageable pageable);
    
    Page<SettlementResponseDto> search(String search, Pageable pageable);
    
    SettlementResponseDto update(Long id, UpdateSettlementDto updateSettlementDto);
    
    void deleteById(Long id);
}

