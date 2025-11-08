package com.icheha.aprendia_api.users.cell.data.repositories;

import com.icheha.aprendia_api.users.cell.data.entities.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {
}

