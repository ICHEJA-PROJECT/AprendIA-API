package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userPersonaRepository")
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    
    Optional<PersonaEntity> findByCurp(String curp);
    
    @Query("SELECT p FROM UserPersonaEntity p " +
           "LEFT JOIN FETCH p.domicilio " +
           "WHERE p.idPersona = :id")
    Optional<PersonaEntity> findByIdWithRelations(@Param("id") Long id);
    // Nota: Se carga el domicilio para obtener los datos de dirección (vialidadNombre, etc.)
    
    @Query("SELECT p.idPersona, p.profileImagePath FROM UserPersonaEntity p WHERE p.idPersona IN :ids")
    java.util.List<Object[]> findProfileImagePathsByIds(@Param("ids") java.util.List<Long> ids);
}

