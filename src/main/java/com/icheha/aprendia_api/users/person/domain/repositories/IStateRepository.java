package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.State;

import java.util.List;
import java.util.Optional;

public interface IStateRepository {
    
    State save(State state);
    
    Optional<State> findById(Long id);
    
    List<State> findAll();
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}

