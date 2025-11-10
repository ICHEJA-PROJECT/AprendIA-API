package com.icheha.aprendia_api.exercises.templates.data.repositories;

import com.icheha.aprendia_api.exercises.templates.data.entities.TypeInstructionMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeInstructionMediaRepository extends JpaRepository<TypeInstructionMediaEntity, Long> {
    
    Optional<TypeInstructionMediaEntity> findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);
}

