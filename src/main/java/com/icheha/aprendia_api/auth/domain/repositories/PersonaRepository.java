package com.icheha.aprendia_api.auth.domain.repositories;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    Optional<Persona> findByCurp(String curp);
    
    @Query("SELECT p FROM Persona p JOIN FETCH p.personaRoles pr JOIN FETCH pr.rol WHERE p.curp = :curp")
    Optional<Persona> findByCurpWithRoles(@Param("curp") String curp);
    
    boolean existsByCurp(String curp);
}
