package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.auth.data.entities.PersonaRolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRolRepository extends JpaRepository<PersonaRolEntity, Long> {
    
    @Query("SELECT pr FROM PersonaRolEntity pr JOIN FETCH pr.rol WHERE pr.persona.idPersona = :personaId")
    Optional<PersonaRolEntity> findByPersonaId(@Param("personaId") Long personaId);
    
    @Query("SELECT pr FROM PersonaRolEntity pr JOIN FETCH pr.rol WHERE pr.idPersona = :personaId")
    java.util.List<PersonaRolEntity> findAllByPersonaId(@Param("personaId") Long personaId);
    
    @Modifying
    @Query("DELETE FROM PersonaRolEntity pr WHERE pr.persona.idPersona = :personaId")
    void deleteByPersonaId(@Param("personaId") Long personaId);
}
