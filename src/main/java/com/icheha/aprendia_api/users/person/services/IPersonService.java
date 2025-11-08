package com.icheha.aprendia_api.users.person.services;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.users.person.data.dtos.CreatePersonDto;
import com.icheha.aprendia_api.users.person.data.dtos.PersonResponseDto;
import com.icheha.aprendia_api.users.person.data.dtos.UpdatePersonDto;

import java.util.List;
import java.util.Optional;

public interface IPersonService {
    
    PersonResponseDto create(CreatePersonDto createPersonDto);
    
    Optional<PersonResponseDto> findById(Long id);
    
    List<PersonResponseDto> findAll();
    
    PersonResponseDto update(Long id, UpdatePersonDto updatePersonDto);
    
    void deleteById(Long id);
}

