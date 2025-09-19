package com.icheha.aprendia_api.auth.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.pivot.PersonaRol;
import com.icheha.aprendia_api.auth.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
    Optional<Rol> findByNombre(String nombre);
    
    boolean existsByNombre(String nombre);
    
    @Query("SELECT pr FROM PersonaRol pr JOIN FETCH pr.rol WHERE pr.persona.idPersona = :personaId")
    Optional<PersonaRol> findByPersonaId(@Param("personaId") Long personaId);
}
