package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.StateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStateService {
    
    StateResponseDto create(CreateStateDto createStateDto);
    
    StateResponseDto findById(Long id);
    
    List<StateResponseDto> findAll();
    
    Page<StateResponseDto> findAll(Pageable pageable);
    
    Page<StateResponseDto> search(String search, Pageable pageable);
    
    StateResponseDto update(Long id, UpdateStateDto updateStateDto);
    
    void deleteById(Long id);
}

