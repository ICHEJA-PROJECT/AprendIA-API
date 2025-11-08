package com.icheha.aprendia_api.users.student.services.impl;

import com.icheha.aprendia_api.users.student.data.dtos.CreateProgenitorDto;
import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;
import com.icheha.aprendia_api.users.student.domain.repositories.IProgenitorRepository;
import com.icheha.aprendia_api.users.student.services.IProgenitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProgenitorServiceImpl implements IProgenitorService {
    
    private final IProgenitorRepository progenitorRepository;
    
    public ProgenitorServiceImpl(IProgenitorRepository progenitorRepository) {
        this.progenitorRepository = progenitorRepository;
    }
    
    @Override
    @Transactional
    public Optional<Progenitor> create(CreateProgenitorDto createProgenitorDto) {
        // Buscar si ya existe por CURP
        Optional<Progenitor> existing = progenitorRepository.findByCurp(createProgenitorDto.getCurp());
        if (existing.isPresent()) {
            return existing;
        }
        
        // Crear nuevo progenitor
        Progenitor progenitor = new Progenitor.Builder()
                .curp(createProgenitorDto.getCurp())
                .primerNombre(createProgenitorDto.getFirstName())
                .segundoNombre(createProgenitorDto.getMiddleName())
                .primerApellido(createProgenitorDto.getPaternalSurname())
                .segundoApellido(createProgenitorDto.getMaternalSurname())
                .build();
        
        Progenitor saved = progenitorRepository.create(progenitor);
        return Optional.of(saved);
    }
}

