package com.icheha.aprendia_api.users.student.domain.repositories;

import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;

import java.util.Optional;

public interface IProgenitorRepository {
    
    Progenitor create(Progenitor progenitor);
    
    Optional<Progenitor> findByCurp(String curp);
}

