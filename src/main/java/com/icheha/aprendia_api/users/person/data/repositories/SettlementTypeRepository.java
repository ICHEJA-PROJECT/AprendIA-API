package com.icheha.aprendia_api.users.person.data.repositories;

import com.icheha.aprendia_api.users.person.data.entities.SettlementTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementTypeRepository extends JpaRepository<SettlementTypeEntity, Long> {
}

