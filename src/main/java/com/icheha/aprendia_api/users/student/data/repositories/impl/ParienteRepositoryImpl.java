package com.icheha.aprendia_api.users.student.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import com.icheha.aprendia_api.users.student.data.entities.ParienteEntity;
import com.icheha.aprendia_api.users.student.data.entities.RolParienteEntity;
import com.icheha.aprendia_api.users.student.data.mappers.ParienteMapper;
import com.icheha.aprendia_api.users.student.data.repositories.ParienteRepository;
import com.icheha.aprendia_api.users.student.data.repositories.RolParienteRepository;
import com.icheha.aprendia_api.users.student.domain.entities.Pariente;
import com.icheha.aprendia_api.users.student.domain.repositories.IParienteRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ParienteRepositoryImpl implements IParienteRepository {
    
    private final ParienteRepository parienteRepository;
    private final PersonaRepository personaRepository;
    private final RolParienteRepository rolParienteRepository;
    private final ParienteMapper parienteMapper;
    
    public ParienteRepositoryImpl(@Lazy ParienteRepository parienteRepository,
                                @Lazy PersonaRepository personaRepository,
                                @Lazy RolParienteRepository rolParienteRepository,
                                ParienteMapper parienteMapper) {
        this.parienteRepository = parienteRepository;
        this.personaRepository = personaRepository;
        this.rolParienteRepository = rolParienteRepository;
        this.parienteMapper = parienteMapper;
    }
    
    @Override
    public Pariente create(Pariente pariente) {
        if (pariente == null) {
            throw new IllegalArgumentException("Pariente no puede ser nulo");
        }
        
        ParienteEntity entity = parienteMapper.toEntity(pariente);
        
        // Establecer relaciones
        PersonaEntity personaEntity = personaRepository.findById(pariente.getPersona().getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + pariente.getPersona().getIdPersona()));
        PersonaEntity parienteEntity = personaRepository.findById(pariente.getPariente().getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Pariente no encontrado con ID: " + pariente.getPariente().getIdPersona()));
        RolParienteEntity rolParienteEntity = rolParienteRepository.findById(pariente.getRolPariente().getId())
                .orElseThrow(() -> new IllegalArgumentException("RolPariente no encontrado con ID: " + pariente.getRolPariente().getId()));
        
        entity.setPersona(personaEntity);
        entity.setPariente(parienteEntity);
        entity.setRolPariente(rolParienteEntity);
        
        ParienteEntity savedEntity = parienteRepository.save(entity);
        return parienteMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<Pariente> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return parienteRepository.findById(id)
                .map(parienteMapper::toDomain);
    }
    
    @Override
    public List<Pariente> findByPersonaId(Long personaId) {
        if (personaId == null) {
            return List.of();
        }
        return parienteRepository.findByPersonaId(personaId).stream()
                .map(parienteMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Pariente> findByParienteId(Long parienteId) {
        if (parienteId == null) {
            return List.of();
        }
        return parienteRepository.findByParienteId(parienteId).stream()
                .map(parienteMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Pariente> findByPersonaIdAndRolNombre(Long personaId, String rolNombre) {
        if (personaId == null || rolNombre == null || rolNombre.trim().isEmpty()) {
            return List.of();
        }
        return parienteRepository.findByPersonaIdAndRolNombre(personaId, rolNombre).stream()
                .map(parienteMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Pariente> findByPersonaIdAndParienteIdAndRolParienteId(Long personaId, Long parienteId, Long rolParienteId) {
        if (personaId == null || parienteId == null || rolParienteId == null) {
            return Optional.empty();
        }
        return parienteRepository.findByPersonaIdAndParienteIdAndRolParienteId(personaId, parienteId, rolParienteId)
                .map(parienteMapper::toDomain);
    }
    
    @Override
    public List<Pariente> findAll() {
        return parienteRepository.findAll().stream()
                .map(parienteMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Pariente update(Pariente pariente) {
        if (pariente == null || pariente.getId() == null) {
            throw new IllegalArgumentException("Pariente y su ID no pueden ser nulos");
        }
        
        ParienteEntity entity = parienteRepository.findById(pariente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Pariente no encontrado con ID: " + pariente.getId()));
        
        // Actualizar relaciones si es necesario
        PersonaEntity personaEntity = personaRepository.findById(pariente.getPersona().getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + pariente.getPersona().getIdPersona()));
        PersonaEntity parienteEntity = personaRepository.findById(pariente.getPariente().getIdPersona())
                .orElseThrow(() -> new IllegalArgumentException("Pariente no encontrado con ID: " + pariente.getPariente().getIdPersona()));
        RolParienteEntity rolParienteEntity = rolParienteRepository.findById(pariente.getRolPariente().getId())
                .orElseThrow(() -> new IllegalArgumentException("RolPariente no encontrado con ID: " + pariente.getRolPariente().getId()));
        
        entity.setPersona(personaEntity);
        entity.setPariente(parienteEntity);
        entity.setRolPariente(rolParienteEntity);
        
        ParienteEntity savedEntity = parienteRepository.save(entity);
        return parienteMapper.toDomain(savedEntity);
    }
    
    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        parienteRepository.deleteById(id);
    }
}

