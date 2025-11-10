package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.request.UpdateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;

import java.util.List;
import java.util.Optional;

public interface ITypeInstructionMediaService {
    
    TypeInstructionMediaResponseDto createTypeInstructionMedia(CreateTypeInstructionMediaDto createTypeInstructionMediaDto);
    
    List<TypeInstructionMediaResponseDto> getAllTypeInstructionMedias();
    
    Optional<TypeInstructionMediaResponseDto> findById(Long id);
    
    TypeInstructionMediaResponseDto update(Long id, UpdateTypeInstructionMediaDto updateTypeInstructionMediaDto);
    
    void delete(Long id);
}
