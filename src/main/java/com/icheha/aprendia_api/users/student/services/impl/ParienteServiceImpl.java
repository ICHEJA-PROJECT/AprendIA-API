package com.icheha.aprendia_api.users.student.services.impl;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.users.person.domain.repositories.IPersonaRepository;
import com.icheha.aprendia_api.users.student.data.dtos.CreateParienteDto;
import com.icheha.aprendia_api.users.student.data.dtos.ParienteResponseDto;
import com.icheha.aprendia_api.users.student.data.dtos.UpdateParienteDto;
import com.icheha.aprendia_api.users.student.domain.entities.Pariente;
import com.icheha.aprendia_api.users.student.domain.entities.RolPariente;
import com.icheha.aprendia_api.users.student.domain.repositories.IParienteRepository;
import com.icheha.aprendia_api.users.student.domain.repositories.IRolParienteRepository;
import com.icheha.aprendia_api.users.student.services.IParienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParienteServiceImpl implements IParienteService {
    
    private final IParienteRepository parienteRepository;
    private final IPersonaRepository personaRepository;
    private final IRolParienteRepository rolParienteRepository;
    
    public ParienteServiceImpl(IParienteRepository parienteRepository,
                              @Qualifier("userPersonaRepositoryImpl") IPersonaRepository personaRepository,
                              IRolParienteRepository rolParienteRepository) {
        this.parienteRepository = parienteRepository;
        this.personaRepository = personaRepository;
        this.rolParienteRepository = rolParienteRepository;
    }
    
    @Override
    @Transactional
    public ParienteResponseDto create(CreateParienteDto createParienteDto) {
        // Validar que la persona existe
        Persona persona = personaRepository.findById(createParienteDto.getPersonaId())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + createParienteDto.getPersonaId()));
        
        // Validar que el pariente existe
        Persona pariente = personaRepository.findById(createParienteDto.getParienteId())
                .orElseThrow(() -> new IllegalArgumentException("Pariente no encontrado con ID: " + createParienteDto.getParienteId()));
        
        // Validar que el rol de pariente existe
        RolPariente rolPariente = rolParienteRepository.findById(createParienteDto.getRolParienteId())
                .orElseThrow(() -> new IllegalArgumentException("Rol de pariente no encontrado con ID: " + createParienteDto.getRolParienteId()));
        
        // Verificar si ya existe esta relación
        Optional<Pariente> existing = parienteRepository.findByPersonaIdAndParienteIdAndRolParienteId(
                createParienteDto.getPersonaId(),
                createParienteDto.getParienteId(),
                createParienteDto.getRolParienteId()
        );
        
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Ya existe esta relación de pariente");
        }
        
        Pariente parienteDomain = new Pariente.Builder()
                .persona(persona)
                .pariente(pariente)
                .rolPariente(rolPariente)
                .build();
        
        Pariente saved = parienteRepository.create(parienteDomain);
        return toResponseDto(saved);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ParienteResponseDto> findAll() {
        return parienteRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ParienteResponseDto> findById(Long id) {
        return parienteRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ParienteResponseDto> findByPersonaId(Long personaId) {
        return parienteRepository.findByPersonaId(personaId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ParienteResponseDto> findByParienteId(Long parienteId) {
        return parienteRepository.findByParienteId(parienteId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ParienteResponseDto> findByPersonaIdAndRolNombre(Long personaId, String rolNombre) {
        return parienteRepository.findByPersonaIdAndRolNombre(personaId, rolNombre).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public ParienteResponseDto update(Long id, UpdateParienteDto updateParienteDto) {
        Pariente existing = parienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pariente no encontrado con ID: " + id));
        
        // Validar que la persona existe
        Persona persona = personaRepository.findById(updateParienteDto.getPersonaId())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + updateParienteDto.getPersonaId()));
        
        // Validar que el pariente existe
        Persona pariente = personaRepository.findById(updateParienteDto.getParienteId())
                .orElseThrow(() -> new IllegalArgumentException("Pariente no encontrado con ID: " + updateParienteDto.getParienteId()));
        
        // Validar que el rol de pariente existe
        RolPariente rolPariente = rolParienteRepository.findById(updateParienteDto.getRolParienteId())
                .orElseThrow(() -> new IllegalArgumentException("Rol de pariente no encontrado con ID: " + updateParienteDto.getRolParienteId()));
        
        // Verificar si ya existe esta relación en otro registro
        Optional<Pariente> existingRelation = parienteRepository.findByPersonaIdAndParienteIdAndRolParienteId(
                updateParienteDto.getPersonaId(),
                updateParienteDto.getParienteId(),
                updateParienteDto.getRolParienteId()
        );
        
        if (existingRelation.isPresent() && !existingRelation.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe esta relación de pariente");
        }
        
        Pariente updated = new Pariente.Builder()
                .id(existing.getId())
                .persona(persona)
                .pariente(pariente)
                .rolPariente(rolPariente)
                .build();
        
        Pariente saved = parienteRepository.update(updated);
        return toResponseDto(saved);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        Pariente existing = parienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pariente no encontrado con ID: " + id));
        
        parienteRepository.delete(id);
    }
    
    private ParienteResponseDto toResponseDto(Pariente pariente) {
        ParienteResponseDto dto = new ParienteResponseDto();
        dto.setId(pariente.getId());
        dto.setPersonaId(pariente.getPersona().getIdPersona());
        dto.setParienteId(pariente.getPariente().getIdPersona());
        dto.setRolParienteId(pariente.getRolPariente().getId());
        dto.setRolParienteNombre(pariente.getRolPariente().getNombre());
        
        // Obtener información del pariente
        String nombreCompleto = pariente.getPariente().getNombreCompletoConApellidos();
        dto.setParienteNombreCompleto(nombreCompleto);
        dto.setParienteCurp(pariente.getPariente().getCurp() != null ? pariente.getPariente().getCurp().getValue() : null);
        
        return dto;
    }
}

