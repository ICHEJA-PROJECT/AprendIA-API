package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.repositories.IExerciseRepository;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.EjercicioPuntajeResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.EjercicioPuntajeEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.repositories.EjercicioPuntajeRepository;
import com.icheha.aprendia_api.records.pupilExcerise.services.IEjercicioPuntajeService;
import com.icheha.aprendia_api.auth.data.repositories.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EjercicioPuntajeServiceImpl implements IEjercicioPuntajeService {
    
    @Autowired
    private EjercicioPuntajeRepository ejercicioPuntajeRepository;
    
    @Autowired
    private IExerciseRepository exerciseRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    @Transactional
    public EjercicioPuntajeResponseDto create(CreateEjercicioPuntajeDto createDto) {
        // Validar que el ejercicio existe
        exerciseRepository.findById(createDto.getIdEjercicio())
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + createDto.getIdEjercicio()));
        
        // Validar que la persona existe
        personaRepository.findById(createDto.getIdPersona())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + createDto.getIdPersona()));
        
        // Crear nueva entidad
        EjercicioPuntajeEntity entity = new EjercicioPuntajeEntity();
        entity.setIdEjercicio(createDto.getIdEjercicio());
        entity.setIdPersona(createDto.getIdPersona());
        entity.setPuntaje(createDto.getPuntaje());
        entity.setFechaCompletado(createDto.getFechaCompletado());
        
        // Guardar en la base de datos
        EjercicioPuntajeEntity savedEntity = ejercicioPuntajeRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EjercicioPuntajeResponseDto> findAll() {
        List<EjercicioPuntajeEntity> entities = ejercicioPuntajeRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EjercicioPuntajeResponseDto> findById(Long id) {
        return ejercicioPuntajeRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public EjercicioPuntajeResponseDto update(Long id, UpdateEjercicioPuntajeDto updateDto) {
        EjercicioPuntajeEntity entity = ejercicioPuntajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EjercicioPuntaje no encontrado con ID: " + id));
        
        // Validar y actualizar ejercicio si se proporciona
        if (updateDto.getIdEjercicio() != null) {
            exerciseRepository.findById(updateDto.getIdEjercicio())
                    .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + updateDto.getIdEjercicio()));
            entity.setIdEjercicio(updateDto.getIdEjercicio());
        }
        
        // Validar y actualizar persona si se proporciona
        if (updateDto.getIdPersona() != null) {
            personaRepository.findById(updateDto.getIdPersona())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + updateDto.getIdPersona()));
            entity.setIdPersona(updateDto.getIdPersona());
        }
        
        // Actualizar puntaje si se proporciona
        if (updateDto.getPuntaje() != null) {
            entity.setPuntaje(updateDto.getPuntaje());
        }
        
        // Actualizar fecha completado si se proporciona
        if (updateDto.getFechaCompletado() != null) {
            entity.setFechaCompletado(updateDto.getFechaCompletado());
        }
        
        // Guardar cambios
        EjercicioPuntajeEntity updatedEntity = ejercicioPuntajeRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        EjercicioPuntajeEntity entity = ejercicioPuntajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EjercicioPuntaje no encontrado con ID: " + id));
        ejercicioPuntajeRepository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EjercicioPuntajeResponseDto> findByEjercicioId(Long idEjercicio) {
        List<EjercicioPuntajeEntity> entities = ejercicioPuntajeRepository.findByEjercicioId(idEjercicio);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<EjercicioPuntajeResponseDto> findByPersonaId(Long idPersona) {
        List<EjercicioPuntajeEntity> entities = ejercicioPuntajeRepository.findByPersonaId(idPersona);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private EjercicioPuntajeResponseDto toResponseDto(EjercicioPuntajeEntity entity) {
        EjercicioPuntajeResponseDto dto = new EjercicioPuntajeResponseDto();
        dto.setIdEjercicioPuntaje(entity.getIdEjercicioPuntaje());
        dto.setIdEjercicio(entity.getIdEjercicio());
        dto.setIdPersona(entity.getIdPersona());
        dto.setPuntaje(entity.getPuntaje());
        dto.setFechaCompletado(entity.getFechaCompletado());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        
        // Cargar información adicional si las relaciones están cargadas
        if (entity.getEjercicio() != null) {
            dto.setEjercicioNombre("Ejercicio " + entity.getIdEjercicio());
        }
        if (entity.getPersona() != null) {
            String nombreCompleto = entity.getPersona().getPrimerNombre() + " " + 
                                   (entity.getPersona().getSegundoNombre() != null ? entity.getPersona().getSegundoNombre() + " " : "") +
                                   entity.getPersona().getPrimerApellido() + " " +
                                   (entity.getPersona().getSegundoApellido() != null ? entity.getPersona().getSegundoApellido() : "");
            dto.setUsuarioNombre(nombreCompleto.trim());
        }
        
        return dto;
    }
}

