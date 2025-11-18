package com.icheha.aprendia_api.records.pupilExcerise.services;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.EjercicioPuntajeResponseDto;

import java.util.List;
import java.util.Optional;

public interface IEjercicioPuntajeService {
    
    EjercicioPuntajeResponseDto create(CreateEjercicioPuntajeDto createDto);
    
    List<EjercicioPuntajeResponseDto> findAll();
    
    Optional<EjercicioPuntajeResponseDto> findById(Long id);
    
    EjercicioPuntajeResponseDto update(Long id, UpdateEjercicioPuntajeDto updateDto);
    
    void delete(Long id);
    
    List<EjercicioPuntajeResponseDto> findByEjercicioId(Long idEjercicio);
    
    List<EjercicioPuntajeResponseDto> findByPersonaId(Long idPersona);
}

