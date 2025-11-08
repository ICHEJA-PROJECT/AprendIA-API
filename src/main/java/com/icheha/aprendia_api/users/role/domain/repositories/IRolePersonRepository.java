package com.icheha.aprendia_api.users.role.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;

import java.util.List;
import java.util.Optional;

public interface IRolePersonRepository {
    
    PersonaRol create(PersonaRol personaRol);
    
    List<PersonaRol> createMany(List<PersonaRol> personaRoles);
    
    Optional<PersonaRol> findById(Long id);
    
    List<PersonaRol> findByPersonId(Long personId);
}

