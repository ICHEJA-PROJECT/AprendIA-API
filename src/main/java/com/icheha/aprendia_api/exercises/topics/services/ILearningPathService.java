package com.icheha.aprendia_api.exercises.topics.services;

import com.icheha.aprendia_api.exercises.topics.data.dtos.request.CreateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.request.UpdateLearningPathDto;
import com.icheha.aprendia_api.exercises.topics.data.dtos.response.LearningPathResponseDto;

import java.util.List;

public interface ILearningPathService {
    
    LearningPathResponseDto create(CreateLearningPathDto createLearningPathDto);
    
    List<LearningPathResponseDto> findAll();
    
    LearningPathResponseDto findById(Long id);
    
    LearningPathResponseDto update(Long id, UpdateLearningPathDto updateLearningPathDto);
    
    void deleteById(Long id);
    
    List<LearningPathResponseDto> findByIdPerfil(Long idPerfil);
    
    List<LearningPathResponseDto> findByIdMetodologia(Long idMetodologia);
    
    List<LearningPathResponseDto> findByNombreContaining(String nombre);
}

