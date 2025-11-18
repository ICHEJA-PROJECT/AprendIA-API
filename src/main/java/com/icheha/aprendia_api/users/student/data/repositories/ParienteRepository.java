package com.icheha.aprendia_api.users.student.data.repositories;

import com.icheha.aprendia_api.users.student.data.entities.ParienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParienteRepository extends JpaRepository<ParienteEntity, Long> {
    
    @Override
    @Query("SELECT p FROM ParienteEntity p LEFT JOIN FETCH p.rolPariente")
    List<ParienteEntity> findAll();
    
    @Query("SELECT p FROM ParienteEntity p LEFT JOIN FETCH p.rolPariente WHERE p.persona.idPersona = :personaId")
    List<ParienteEntity> findByPersonaId(@Param("personaId") Long personaId);
    
    @Query("SELECT p FROM ParienteEntity p LEFT JOIN FETCH p.rolPariente WHERE p.pariente.idPersona = :parienteId")
    List<ParienteEntity> findByParienteId(@Param("parienteId") Long parienteId);
    
    @Query("SELECT p FROM ParienteEntity p LEFT JOIN FETCH p.rolPariente WHERE p.persona.idPersona = :personaId AND p.rolPariente.nombre = :rolNombre")
    List<ParienteEntity> findByPersonaIdAndRolNombre(@Param("personaId") Long personaId, @Param("rolNombre") String rolNombre);
    
    @Query("SELECT p FROM ParienteEntity p LEFT JOIN FETCH p.rolPariente WHERE p.persona.idPersona = :personaId AND p.pariente.idPersona = :parienteId AND p.rolPariente.id = :rolParienteId")
    Optional<ParienteEntity> findByPersonaIdAndParienteIdAndRolParienteId(
            @Param("personaId") Long personaId, 
            @Param("parienteId") Long parienteId, 
            @Param("rolParienteId") Long rolParienteId);
}

