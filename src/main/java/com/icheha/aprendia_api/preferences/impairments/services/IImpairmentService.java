package com.icheha.aprendia_api.preferences.impairments.services;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.UpdateImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.ImpairmentResponseDto;

import java.util.List;

public interface IImpairmentService {
    
    ImpairmentResponseDto create(CreateImpairmentDto dto);
    
    List<ImpairmentResponseDto> findAll();
    
    ImpairmentResponseDto findById(Long id);
    
    ImpairmentResponseDto update(Long id, UpdateImpairmentDto updateImpairmentDto);
    
    void deleteById(Long id);
}

