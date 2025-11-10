package com.icheha.aprendia_api.exercises.templates.services.impl;

import com.icheha.aprendia_api.exercises.templates.data.dtos.request.CreateTypeInstructionMediaDto;
import com.icheha.aprendia_api.exercises.templates.data.dtos.response.TypeInstructionMediaResponseDto;
import com.icheha.aprendia_api.exercises.templates.services.ITypeInstructionMediaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeInstructionMediaServiceImpl implements ITypeInstructionMediaService {
    
    @Override
    public TypeInstructionMediaResponseDto createTypeInstructionMedia(CreateTypeInstructionMediaDto createTypeInstructionMediaDto) {
        TypeInstructionMediaResponseDto typeInstructionMedia = new TypeInstructionMediaResponseDto();
        typeInstructionMedia.setId(1L);
        typeInstructionMedia.setName(createTypeInstructionMediaDto.getName());
        typeInstructionMedia.setDescription(createTypeInstructionMediaDto.getDescription());
        return typeInstructionMedia;
    }
    
    @Override
    public List<TypeInstructionMediaResponseDto> getAllTypeInstructionMedias() {
        List<TypeInstructionMediaResponseDto> typeInstructionMedias = new ArrayList<>();
        
        TypeInstructionMediaResponseDto typeInstructionMedia1 = new TypeInstructionMediaResponseDto();
        typeInstructionMedia1.setId(1L);
        typeInstructionMedia1.setName("Type Instruction Media 1");
        typeInstructionMedia1.setDescription("Descripción del type instruction media 1");
        typeInstructionMedias.add(typeInstructionMedia1);
        
        return typeInstructionMedias;
    }
}
