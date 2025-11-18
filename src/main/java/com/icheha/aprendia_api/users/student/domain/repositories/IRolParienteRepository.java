package com.icheha.aprendia_api.users.student.domain.repositories;

import com.icheha.aprendia_api.users.student.domain.entities.RolPariente;

import java.util.List;
import java.util.Optional;

public interface IRolParienteRepository {
    
    RolPariente create(RolPariente rolPariente);
    
    Optional<RolPariente> findById(Long id);
    
    Optional<RolPariente> findByNombre(String nombre);
    
    List<RolPariente> findAll();
    
    RolPariente update(RolPariente rolPariente);
    
    void delete(Long id);
}

