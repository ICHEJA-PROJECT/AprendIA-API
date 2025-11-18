package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.repositories.IExerciseRepository;
import com.icheha.aprendia_api.exercises.templates.data.repositories.SkillRepository;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateHabilidadPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.HabilidadPuntajeResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.HabilidadPuntajeEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.repositories.HabilidadPuntajeRepository;
import com.icheha.aprendia_api.records.pupilExcerise.services.IHabilidadPuntajeService;
import com.icheha.aprendia_api.auth.data.repositories.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabilidadPuntajeServiceImpl implements IHabilidadPuntajeService {
    
    @Autowired
    private HabilidadPuntajeRepository habilidadPuntajeRepository;
    
    @Autowired
    private IExerciseRepository exerciseRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    @Transactional
    public HabilidadPuntajeResponseDto create(CreateHabilidadPuntajeDto createDto) {
        // Validar que el ejercicio existe
        exerciseRepository.findById(createDto.getIdEjercicio())
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + createDto.getIdEjercicio()));
        
        // Validar que la habilidad existe
        skillRepository.findById(createDto.getIdHabilidad())
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + createDto.getIdHabilidad()));
        
        // Validar que la persona existe
        personaRepository.findById(createDto.getIdPersona())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + createDto.getIdPersona()));
        
        // Crear nueva entidad
        HabilidadPuntajeEntity entity = new HabilidadPuntajeEntity();
        entity.setIdEjercicio(createDto.getIdEjercicio());
        entity.setIdHabilidad(createDto.getIdHabilidad());
        entity.setIdPersona(createDto.getIdPersona());
        entity.setPuntaje(createDto.getPuntaje());
        entity.setFechaCompletado(createDto.getFechaCompletado());
        
        // Guardar en la base de datos
        HabilidadPuntajeEntity savedEntity = habilidadPuntajeRepository.save(entity);
        
        // Convertir a DTO de respuesta
        return toResponseDto(savedEntity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HabilidadPuntajeResponseDto> findAll() {
        List<HabilidadPuntajeEntity> entities = habilidadPuntajeRepository.findAll();
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<HabilidadPuntajeResponseDto> findById(Long id) {
        return habilidadPuntajeRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public HabilidadPuntajeResponseDto update(Long id, UpdateHabilidadPuntajeDto updateDto) {
        HabilidadPuntajeEntity entity = habilidadPuntajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HabilidadPuntaje no encontrado con ID: " + id));
        
        // Validar y actualizar ejercicio si se proporciona
        if (updateDto.getIdEjercicio() != null) {
            exerciseRepository.findById(updateDto.getIdEjercicio())
                    .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + updateDto.getIdEjercicio()));
            entity.setIdEjercicio(updateDto.getIdEjercicio());
        }
        
        // Validar y actualizar habilidad si se proporciona
        if (updateDto.getIdHabilidad() != null) {
            skillRepository.findById(updateDto.getIdHabilidad())
                    .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con ID: " + updateDto.getIdHabilidad()));
            entity.setIdHabilidad(updateDto.getIdHabilidad());
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
        HabilidadPuntajeEntity updatedEntity = habilidadPuntajeRepository.save(entity);
        return toResponseDto(updatedEntity);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        HabilidadPuntajeEntity entity = habilidadPuntajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HabilidadPuntaje no encontrado con ID: " + id));
        habilidadPuntajeRepository.delete(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HabilidadPuntajeResponseDto> findByEjercicioId(Long idEjercicio) {
        List<HabilidadPuntajeEntity> entities = habilidadPuntajeRepository.findByEjercicioId(idEjercicio);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HabilidadPuntajeResponseDto> findByHabilidadId(Long idHabilidad) {
        List<HabilidadPuntajeEntity> entities = habilidadPuntajeRepository.findByHabilidadId(idHabilidad);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<HabilidadPuntajeResponseDto> findByPersonaId(Long idPersona) {
        List<HabilidadPuntajeEntity> entities = habilidadPuntajeRepository.findByPersonaId(idPersona);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private HabilidadPuntajeResponseDto toResponseDto(HabilidadPuntajeEntity entity) {
        HabilidadPuntajeResponseDto dto = new HabilidadPuntajeResponseDto();
        dto.setIdHabilidadPuntaje(entity.getIdHabilidadPuntaje());
        dto.setIdEjercicio(entity.getIdEjercicio());
        dto.setIdHabilidad(entity.getIdHabilidad());
        dto.setIdPersona(entity.getIdPersona());
        dto.setPuntaje(entity.getPuntaje());
        dto.setFechaCompletado(entity.getFechaCompletado());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        
        // Cargar información adicional si las relaciones están cargadas
        if (entity.getEjercicio() != null) {
            dto.setEjercicioNombre("Ejercicio " + entity.getIdEjercicio());
        }
        if (entity.getHabilidad() != null) {
            dto.setHabilidadNombre(entity.getHabilidad().getNombre());
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

