package com.icheha.aprendia_api.users.cell.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.users.cell.data.entities.CellEntity;
import com.icheha.aprendia_api.users.cell.data.entities.InstitutionEntity;
import com.icheha.aprendia_api.users.cell.data.mappers.CellMapper;
import com.icheha.aprendia_api.users.cell.data.repositories.CellRepository;
import com.icheha.aprendia_api.users.cell.data.repositories.InstitutionRepository;
import com.icheha.aprendia_api.users.cell.domain.entities.Cell;
import com.icheha.aprendia_api.users.cell.domain.repositories.ICellRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CellRepositoryImpl implements ICellRepository {
    
    private final CellRepository cellRepository;
    private final InstitutionRepository institutionRepository;
    private final PersonaRepository personaRepository;
    private final CellMapper cellMapper;
    private final EntityManager entityManager;
    
    public CellRepositoryImpl(@Lazy CellRepository cellRepository,
                             @Lazy InstitutionRepository institutionRepository,
                             @Lazy @Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                             CellMapper cellMapper,
                             EntityManager entityManager) {
        this.cellRepository = cellRepository;
        this.institutionRepository = institutionRepository;
        this.personaRepository = personaRepository;
        this.cellMapper = cellMapper;
        this.entityManager = entityManager;
    }
    
    @Override
    public Cell create(Cell cell, Long institutionId, Long coordinatorId) {
        if (cell == null) {
            throw new IllegalArgumentException("Cell no puede ser nulo");
        }
        if (institutionId == null) {
            throw new IllegalArgumentException("ID de institución no puede ser nulo");
        }
        if (coordinatorId == null) {
            throw new IllegalArgumentException("ID de coordinador no puede ser nulo");
        }
        
        InstitutionEntity institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new IllegalArgumentException("Institución no encontrada con ID: " + institutionId));
        
        // Verificar que el coordinador existe usando el repositorio
        if (!personaRepository.existsById(coordinatorId)) {
            throw new IllegalArgumentException("Coordinador no encontrado con ID: " + coordinatorId);
        }
        
        // Usar EntityManager.getReference para obtener la referencia proxy correcta
        // Esto asegura que se use la entidad PersonaEntity del módulo users/person
        PersonaEntity coordinator = entityManager.getReference(
                com.icheha.aprendia_api.users.person.data.entities.PersonaEntity.class, 
                coordinatorId);
        
        CellEntity entity = cellMapper.toEntity(cell);
        entity.setInstitution(institution);
        entity.setCoordinator(coordinator);
        
        CellEntity savedEntity = cellRepository.save(entity);
        return cellMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Cell> findAll() {
        return cellRepository.findAll().stream()
                .map(cellMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Cell> findByInstitution(Long institutionId) {
        if (institutionId == null) {
            return List.of();
        }
        return cellRepository.findByInstitutionIdWithCoordinator(institutionId).stream()
                .map(cellMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Cell> findByCoordinator(Long coordinatorId) {
        if (coordinatorId == null) {
            return List.of();
        }
        return cellRepository.findByCoordinatorId(coordinatorId).stream()
                .map(cellMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Cell> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return cellRepository.findByIdWithRelations(id)
                .map(cellMapper::toDomain);
    }
    
    @Override
    public Cell update(Cell cell, Long institutionId, Long coordinatorId) {
        if (cell == null || cell.getId() == null) {
            throw new IllegalArgumentException("Cell y su ID no pueden ser nulos");
        }
        
        CellEntity entity = cellRepository.findByIdWithRelations(cell.getId())
                .orElseThrow(() -> new IllegalArgumentException("Célula no encontrada con ID: " + cell.getId()));
        
        if (institutionId != null) {
            InstitutionEntity institution = institutionRepository.findById(institutionId)
                    .orElseThrow(() -> new IllegalArgumentException("Institución no encontrada con ID: " + institutionId));
            entity.setInstitution(institution);
        }
        
        if (coordinatorId != null) {
            if (!personaRepository.existsById(coordinatorId)) {
                throw new IllegalArgumentException("Coordinador no encontrado con ID: " + coordinatorId);
            }
            PersonaEntity coordinator = entityManager.getReference(
                    com.icheha.aprendia_api.users.person.data.entities.PersonaEntity.class, 
                    coordinatorId);
            entity.setCoordinator(coordinator);
        }
        
        if (cell.getStartDate() != null) {
            entity.setStartDate(cell.getStartDate());
        }
        
        if (cell.getEndDate() != null) {
            entity.setEndDate(cell.getEndDate());
        }
        
        CellEntity updatedEntity = cellRepository.save(entity);
        return cellMapper.toDomain(updatedEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        if (!cellRepository.existsById(id)) {
            throw new IllegalArgumentException("Célula no encontrada con ID: " + id);
        }
        cellRepository.deleteById(id);
    }
}

