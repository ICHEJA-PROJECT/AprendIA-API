package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.users.person.data.dtos.request.CreateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.request.UpdateStateDto;
import com.icheha.aprendia_api.users.person.data.dtos.response.StateResponseDto;
import com.icheha.aprendia_api.users.person.data.mappers.StateMapper;
import com.icheha.aprendia_api.users.person.data.repositories.StateRepository;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import com.icheha.aprendia_api.users.person.domain.repositories.IStateRepository;
import com.icheha.aprendia_api.users.person.services.IStateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements IStateService {
    
    private final IStateRepository stateRepository;
    private final StateRepository stateJpaRepository;
    private final StateMapper stateMapper;
    
    public StateServiceImpl(IStateRepository stateRepository,
                           StateRepository stateJpaRepository,
                           StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateJpaRepository = stateJpaRepository;
        this.stateMapper = stateMapper;
    }
    
    @Override
    @Transactional
    public StateResponseDto create(CreateStateDto createStateDto) {
        State state = new State.Builder()
                .nombre(createStateDto.getNombre())
                .build();
        
        State savedState = stateRepository.save(state);
        return toResponseDto(savedState);
    }
    
    @Override
    @Transactional(readOnly = true)
    public StateResponseDto findById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + id));
        return toResponseDto(state);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<StateResponseDto> findAll() {
        return stateRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<StateResponseDto> findAll(Pageable pageable) {
        return stateJpaRepository.findAll(pageable)
                .map(entity -> {
                    State state = stateMapper.toDomain(entity);
                    return toResponseDto(state);
                });
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<StateResponseDto> search(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return stateJpaRepository.findAll(pageable)
                    .map(entity -> {
                        State state = stateMapper.toDomain(entity);
                        return toResponseDto(state);
                    });
        }
        return stateJpaRepository.search(search.trim(), pageable)
                .map(entity -> {
                    State state = stateMapper.toDomain(entity);
                    return toResponseDto(state);
                });
    }
    
    @Override
    @Transactional
    public StateResponseDto update(Long id, UpdateStateDto updateStateDto) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado no encontrado con ID: " + id));
        
        State updatedState = new State.Builder()
                .id(existingState.getId())
                .nombre(updateStateDto.getNombre() != null ? updateStateDto.getNombre() : existingState.getNombre())
                .build();
        
        State savedState = stateRepository.save(updatedState);
        return toResponseDto(savedState);
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!stateRepository.existsById(id)) {
            throw new EntityNotFoundException("Estado no encontrado con ID: " + id);
        }
        stateRepository.deleteById(id);
    }
    
    private StateResponseDto toResponseDto(State state) {
        StateResponseDto dto = new StateResponseDto();
        dto.setId(state.getId());
        dto.setNombre(state.getNombre());
        return dto;
    }
}

