package com.icheha.aprendia_api.users.person.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.mappers.StateMapper;
import com.icheha.aprendia_api.users.person.data.repositories.StateRepository;
import com.icheha.aprendia_api.users.person.domain.entities.State;
import com.icheha.aprendia_api.users.person.domain.repositories.IStateRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StateRepositoryImpl implements IStateRepository {
    
    private final StateRepository stateRepository;
    private final StateMapper stateMapper;
    
    public StateRepositoryImpl(@Lazy StateRepository stateRepository,
                              StateMapper stateMapper) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
    }
    
    @Override
    public State save(State state) {
        if (state == null) {
            throw new IllegalArgumentException("State no puede ser nulo");
        }
        com.icheha.aprendia_api.users.person.data.entities.StateEntity entity = stateMapper.toEntity(state);
        com.icheha.aprendia_api.users.person.data.entities.StateEntity savedEntity = stateRepository.save(entity);
        return stateMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<State> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return stateRepository.findById(id)
                .map(stateMapper::toDomain);
    }
    
    @Override
    public List<State> findAll() {
        return stateRepository.findAll().stream()
                .map(stateMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        stateRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }
        return stateRepository.existsById(id);
    }
}

