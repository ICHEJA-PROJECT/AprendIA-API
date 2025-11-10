package com.icheha.aprendia_api.users.cell.domain.repositories;

import com.icheha.aprendia_api.users.cell.domain.entities.Institution;

import java.util.List;
import java.util.Optional;

public interface IInstitutionRepository {
    
    Institution create(Institution institution);
    
    List<Institution> findAll();
    
    Optional<Institution> findById(Long id);
    
    Institution update(Institution institution);
    
    void delete(Long id);
}

