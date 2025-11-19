package com.icheha.aprendia_api.users.person.domain.repositories;

import com.icheha.aprendia_api.users.person.domain.entities.SettlementType;

import java.util.List;
import java.util.Optional;

public interface ISettlementTypeRepository {
    
    SettlementType save(SettlementType settlementType);
    
    Optional<SettlementType> findById(Long id);
    
    List<SettlementType> findAll();
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
}

