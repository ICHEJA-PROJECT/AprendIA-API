package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.auth.data.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    
    Optional<PersonaEntity> findByCurp(String curp);
    
    @Query("SELECT p FROM PersonaEntity p JOIN FETCH p.personaRoles pr JOIN FETCH pr.rol WHERE p.curp = :curp")
    Optional<PersonaEntity> findByCurpWithRoles(@Param("curp") String curp);
    
    boolean existsByCurp(String curp);
}
