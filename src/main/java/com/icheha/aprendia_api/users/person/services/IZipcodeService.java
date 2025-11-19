package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateZipcodeDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.ZipcodeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IZipcodeService {
    
    ZipcodeResponseDto create(CreateZipcodeDto createZipcodeDto);
    
    ZipcodeResponseDto findById(Long id);
    
    List<ZipcodeResponseDto> findAll();
    
    Page<ZipcodeResponseDto> findAll(Pageable pageable);
    
    Page<ZipcodeResponseDto> search(String search, Pageable pageable);
    
    ZipcodeResponseDto update(Long id, UpdateZipcodeDto updateZipcodeDto);
    
    void deleteById(Long id);
}

