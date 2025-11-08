package com.icheha.aprendia_api.users.student.data.repositories;

import com.icheha.aprendia_api.users.student.data.entities.ProgenitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgenitorRepository extends JpaRepository<ProgenitorEntity, Long> {
    
    Optional<ProgenitorEntity> findByCurp(String curp);
}

