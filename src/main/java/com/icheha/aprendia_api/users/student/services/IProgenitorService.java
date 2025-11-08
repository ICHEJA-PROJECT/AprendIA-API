package com.icheha.aprendia_api.users.student.services;

import com.icheha.aprendia_api.users.student.data.dtos.CreateProgenitorDto;
import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;

import java.util.Optional;

public interface IProgenitorService {
    
    Optional<Progenitor> create(CreateProgenitorDto createProgenitorDto);
}

