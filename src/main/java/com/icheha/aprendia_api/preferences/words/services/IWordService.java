package com.icheha.aprendia_api.preferences.words.services;

import com.icheha.aprendia_api.preferences.words.data.dtos.request.CreateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.request.UpdateWordDto;
import com.icheha.aprendia_api.preferences.words.data.dtos.response.WordResponseDto;

import java.util.List;

public interface IWordService {
    
    WordResponseDto create(CreateWordDto dto);
    
    List<WordResponseDto> findAll();
    
    WordResponseDto findById(Long id);
    
    WordResponseDto update(Long id, UpdateWordDto updateWordDto);
    
    void delete(Long id);
}

