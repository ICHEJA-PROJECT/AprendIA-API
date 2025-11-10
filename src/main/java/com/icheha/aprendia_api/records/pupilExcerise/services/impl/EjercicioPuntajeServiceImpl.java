package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.exercises.exercises.data.repositories.IExerciseRepository;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdateEjercicioPuntajeDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.EjercicioPuntajeResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.EjercicioPuntajeEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.repositories.EjercicioPuntajeRepository;
import com.icheha.aprendia_api.records.pupilExcerise.services.IEjercicioPuntajeService;
import com.icheha.aprendia_api.users.user.data.repositories.UserRepository;
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
    private UserRepository userRepository;
    
    @Override
    @Transactional
    public EjercicioPuntajeResponseDto create(CreateEjercicioPuntajeDto createDto) {
        // Validar que el ejercicio existe
        exerciseRepository.findById(createDto.getIdEjercicio())
                .orElseThrow(() -> new EntityNotFoundException("Ejercicio no encontrado con ID: " + createDto.getIdEjercicio()));
        
        // Validar que el usuario existe
        userRepository.findById(createDto.getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + createDto.getIdUser()));
        
        // Crear nueva entidad
        EjercicioPuntajeEntity entity = new EjercicioPuntajeEntity();
        entity.setIdEjercicio(createDto.getIdEjercicio());
        entity.setIdUser(createDto.getIdUser());
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
        
        // Validar y actualizar usuario si se proporciona
        if (updateDto.getIdUser() != null) {
            userRepository.findById(updateDto.getIdUser())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + updateDto.getIdUser()));
            entity.setIdUser(updateDto.getIdUser());
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
    public List<EjercicioPuntajeResponseDto> findByUserId(Long idUser) {
        List<EjercicioPuntajeEntity> entities = ejercicioPuntajeRepository.findByUserId(idUser);
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    // Método helper para convertir entidad a DTO
    private EjercicioPuntajeResponseDto toResponseDto(EjercicioPuntajeEntity entity) {
        EjercicioPuntajeResponseDto dto = new EjercicioPuntajeResponseDto();
        dto.setIdEjercicioPuntaje(entity.getIdEjercicioPuntaje());
        dto.setIdEjercicio(entity.getIdEjercicio());
        dto.setIdUser(entity.getIdUser());
        dto.setPuntaje(entity.getPuntaje());
        dto.setFechaCompletado(entity.getFechaCompletado());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdateAt(entity.getUpdateAt());
        
        // Cargar información adicional si las relaciones están cargadas
        if (entity.getEjercicio() != null) {
            dto.setEjercicioNombre("Ejercicio " + entity.getIdEjercicio());
        }
        if (entity.getUser() != null) {
            dto.setUsuarioNombre(entity.getUser().getUsername());
        }
        
        return dto;
    }
}

