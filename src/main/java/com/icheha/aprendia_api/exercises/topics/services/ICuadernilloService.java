package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateCuadernilloDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.CuadernilloResponseDto;

import java.util.List;
import java.util.Optional;

public interface ICuadernilloService {
    
    CuadernilloResponseDto create(CreateCuadernilloDto createCuadernilloDto);
    
    List<CuadernilloResponseDto> findAll();
    
    Optional<CuadernilloResponseDto> findById(Long id);
    
    List<CuadernilloResponseDto> findByRutaAprendizaje(Long idRutaAprendizaje);
    
    List<CuadernilloResponseDto> findByNombreContaining(String nombre);
    
    CuadernilloResponseDto update(Long id, UpdateCuadernilloDto updateCuadernilloDto);
    
    void delete(Long id);
}

