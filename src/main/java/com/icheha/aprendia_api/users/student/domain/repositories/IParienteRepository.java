package com.icheha.aprendia_api.users.student.domain.repositories;

import com.icheha.aprendia_api.users.student.domain.entities.Pariente;

import java.util.List;
import java.util.Optional;

public interface IParienteRepository {
    
    Pariente create(Pariente pariente);
    
    Optional<Pariente> findById(Long id);
    
    List<Pariente> findByPersonaId(Long personaId);
    
    List<Pariente> findByParienteId(Long parienteId);
    
    List<Pariente> findByPersonaIdAndRolNombre(Long personaId, String rolNombre);
    
    Optional<Pariente> findByPersonaIdAndParienteIdAndRolParienteId(Long personaId, Long parienteId, Long rolParienteId);
    
    List<Pariente> findAll();
    
    Pariente update(Pariente pariente);
    
    void delete(Long id);
}

