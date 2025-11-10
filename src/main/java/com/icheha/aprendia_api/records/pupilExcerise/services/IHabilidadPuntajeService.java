package com.icheha.aprendia_api.records.pupilExcerise.services;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.HabilidadPuntajeResponseDto;

import java.util.List;
import java.util.Optional;

public interface IHabilidadPuntajeService {
    
    HabilidadPuntajeResponseDto create(CreateHabilidadPuntajeDto createDto);
    
    List<HabilidadPuntajeResponseDto> findAll();
    
    Optional<HabilidadPuntajeResponseDto> findById(Long id);
    
    HabilidadPuntajeResponseDto update(Long id, UpdateHabilidadPuntajeDto updateDto);
    
    void delete(Long id);
    
    List<HabilidadPuntajeResponseDto> findByEjercicioId(Long idEjercicio);
    
    List<HabilidadPuntajeResponseDto> findByHabilidadId(Long idHabilidad);
    
    List<HabilidadPuntajeResponseDto> findByUserId(Long idUser);
}

