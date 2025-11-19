package com.icheha.aprendia_api.users.student.data.repositories;

import com.icheha.aprendia_api.users.student.data.entities.RolParienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolParienteRepository extends JpaRepository<RolParienteEntity, Long> {
    
    Optional<RolParienteEntity> findByNombre(String nombre);
    
    @Query("SELECT r FROM RolParienteEntity r")
    List<RolParienteEntity> findAllWithoutRelations();
}

