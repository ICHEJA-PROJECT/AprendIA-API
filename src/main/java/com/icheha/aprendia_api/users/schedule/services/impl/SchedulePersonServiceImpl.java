package com.icheha.aprendia_api.users.schedule.services.impl;

import com.icheha.aprendia_api.users.role.services.IRolePersonService;
import com.icheha.aprendia_api.users.schedule.data.dtos.CreateSchedulePersonDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.SchedulePersonResponseDto;
import com.icheha.aprendia_api.users.schedule.domain.entities.SchedulePerson;
import com.icheha.aprendia_api.users.schedule.domain.repositories.ISchedulePersonRepository;
import com.icheha.aprendia_api.users.schedule.services.ISchedulePersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchedulePersonServiceImpl implements ISchedulePersonService {
    
    private final ISchedulePersonRepository schedulePersonRepository;
    private final IRolePersonService rolePersonService;
    
    public SchedulePersonServiceImpl(ISchedulePersonRepository schedulePersonRepository,
                                     IRolePersonService rolePersonService) {
        this.schedulePersonRepository = schedulePersonRepository;
        this.rolePersonService = rolePersonService;
    }
    
    @Override
    @Transactional
    public SchedulePersonResponseDto create(CreateSchedulePersonDto createSchedulePersonDto) {
        // Validar que la relación persona-rol existe
        Optional<com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto> rolePersonOpt = 
                rolePersonService.findById(createSchedulePersonDto.getRolePersonId());
        
        if (rolePersonOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
                    "Relación persona-rol no encontrada con ID: " + createSchedulePersonDto.getRolePersonId());
        }
        
        com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto rolePerson = rolePersonOpt.get();
        
        // Verificar si ya existe el horario para esta persona
        Optional<SchedulePerson> existing = schedulePersonRepository.findOne(
                createSchedulePersonDto.getScheduleId(), 
                rolePerson.getPersonId());
        
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, 
                    "La persona ya tiene registrado este horario");
        }
        
        // Crear la relación
        SchedulePerson schedulePerson = new SchedulePerson.Builder()
                .rolePersonId(createSchedulePersonDto.getRolePersonId())
                .scheduleId(createSchedulePersonDto.getScheduleId())
                .build();
        
        SchedulePerson saved = schedulePersonRepository.create(
                schedulePerson,
                createSchedulePersonDto.getRolePersonId(),
                createSchedulePersonDto.getScheduleId());
        
        return toResponseDto(saved, rolePerson);
    }
    
    @Override
    public List<SchedulePersonResponseDto> findByPerson(Long personId) {
        return schedulePersonRepository.findByPerson(personId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    private SchedulePersonResponseDto toResponseDto(SchedulePerson schedulePerson) {
        SchedulePersonResponseDto dto = new SchedulePersonResponseDto();
        dto.setRolePersonId(schedulePerson.getRolePersonId());
        dto.setScheduleId(schedulePerson.getScheduleId());
        
        if (schedulePerson.getRolePerson() != null) {
            dto.setPersonId(schedulePerson.getRolePerson().getIdPersona());
            dto.setRoleId(schedulePerson.getRolePerson().getIdRol());
        }
        
        if (schedulePerson.getSchedule() != null) {
            dto.setDay(schedulePerson.getSchedule().getDay());
            dto.setHour(schedulePerson.getSchedule().getHour());
        }
        
        return dto;
    }
    
    private SchedulePersonResponseDto toResponseDto(SchedulePerson schedulePerson, 
                                                    com.icheha.aprendia_api.users.role.data.dtos.RolePersonResponseDto rolePerson) {
        SchedulePersonResponseDto dto = new SchedulePersonResponseDto();
        dto.setRolePersonId(schedulePerson.getRolePersonId());
        dto.setScheduleId(schedulePerson.getScheduleId());
        dto.setPersonId(rolePerson.getPersonId());
        dto.setRoleId(rolePerson.getRoleId());
        
        if (schedulePerson.getSchedule() != null) {
            dto.setDay(schedulePerson.getSchedule().getDay());
            dto.setHour(schedulePerson.getSchedule().getHour());
        }
        
        return dto;
    }
}

