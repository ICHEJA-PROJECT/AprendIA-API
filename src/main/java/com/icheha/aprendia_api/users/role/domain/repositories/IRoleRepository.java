package com.icheha.aprendia_api.users.role.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.Rol;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository {
    
    Rol create(Rol rol);
    
    List<Rol> findAll();
    
    Optional<Rol> findById(Long id);
    
    Rol update(Rol rol);
    
    void deleteById(Long id);
}

