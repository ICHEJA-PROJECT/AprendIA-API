package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateMetodologiaDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.MetodologiaResponseDto;

import java.util.List;
import java.util.Optional;

public interface IMetodologiaService {
    
    MetodologiaResponseDto create(CreateMetodologiaDto createMetodologiaDto);
    
    List<MetodologiaResponseDto> findAll();
    
    Optional<MetodologiaResponseDto> findById(Long id);
    
    List<MetodologiaResponseDto> findByNombreContaining(String nombre);
    
    MetodologiaResponseDto update(Long id, UpdateMetodologiaDto updateMetodologiaDto);
    
    void delete(Long id);
}

