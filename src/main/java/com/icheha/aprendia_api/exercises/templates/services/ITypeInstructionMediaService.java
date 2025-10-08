package com.icheha.aprendia_api.exercises.templates.services;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;

import java.util.List;

public interface ITypeInstructionMediaService {
    
    TypeInstructionMediaResponseDto createTypeInstructionMedia(CreateTypeInstructionMediaDto createTypeInstructionMediaDto);
    
    List<TypeInstructionMediaResponseDto> getAllTypeInstructionMedias();
}
