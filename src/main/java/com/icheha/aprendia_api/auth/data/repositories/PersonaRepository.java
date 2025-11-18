package com.icheha.aprendia_api.auth.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    
    Optional<PersonaEntity> findByCurp(String curp);
    
    @Query("SELECT p FROM UserPersonaEntity p LEFT JOIN FETCH p.user WHERE p.curp = :curp")
    Optional<PersonaEntity> findByCurpWithUser(@Param("curp") String curp);
    
    @Query("SELECT p FROM UserPersonaEntity p " +
           "LEFT JOIN FETCH p.user u " +
           "LEFT JOIN FETCH u.userRoles ur " +
           "LEFT JOIN FETCH ur.rol " +
           "WHERE p.curp = :curp")
    Optional<PersonaEntity> findByCurpWithUserAndRoles(@Param("curp") String curp);
    
    boolean existsByCurp(String curp);
}
