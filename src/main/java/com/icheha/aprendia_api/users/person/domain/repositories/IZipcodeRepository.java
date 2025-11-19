package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.Zipcode;

import java.util.List;
import java.util.Optional;

public interface IZipcodeRepository {
    
    Zipcode save(Zipcode zipcode);
    
    Optional<Zipcode> findById(Long id);
    
    List<Zipcode> findAll();
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}

