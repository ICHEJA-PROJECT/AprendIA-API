package com.icheha.aprendia_api.users.schedule.data.repositories.impl;

import com.icheha.aprendia_api.auth.data.entities.UserRolEntity;
import com.icheha.aprendia_api.auth.data.repositories.UserRolRepository;
import com.icheha.aprendia_api.users.schedule.data.entities.ScheduleEntity;
import com.icheha.aprendia_api.users.schedule.data.entities.SchedulePersonEntity;
import com.icheha.aprendia_api.users.schedule.data.mappers.SchedulePersonMapper;
import com.icheha.aprendia_api.users.schedule.data.repositories.SchedulePersonRepository;
import com.icheha.aprendia_api.users.schedule.data.repositories.ScheduleRepository;
import com.icheha.aprendia_api.users.schedule.domain.entities.SchedulePerson;
import com.icheha.aprendia_api.users.schedule.domain.repositories.ISchedulePersonRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SchedulePersonRepositoryImpl implements ISchedulePersonRepository {
    
    private final SchedulePersonRepository schedulePersonRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRolRepository userRolRepository;
    private final SchedulePersonMapper schedulePersonMapper;
    
    public SchedulePersonRepositoryImpl(@Lazy SchedulePersonRepository schedulePersonRepository,
                                        @Lazy ScheduleRepository scheduleRepository,
                                        @Lazy UserRolRepository userRolRepository,
                                        SchedulePersonMapper schedulePersonMapper) {
        this.schedulePersonRepository = schedulePersonRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRolRepository = userRolRepository;
        this.schedulePersonMapper = schedulePersonMapper;
    }
    
    @Override
    public SchedulePerson create(SchedulePerson schedulePerson, Long rolePersonId, Long scheduleId) {
        if (schedulePerson == null) {
            throw new IllegalArgumentException("SchedulePerson no puede ser nulo");
        }
        if (rolePersonId == null) {
            throw new IllegalArgumentException("ID de persona-rol no puede ser nulo");
        }
        if (scheduleId == null) {
            throw new IllegalArgumentException("ID de horario no puede ser nulo");
        }
        
        UserRolEntity rolePerson = userRolRepository.findById(rolePersonId)
                .orElseThrow(() -> new IllegalArgumentException("UserRol no encontrado con ID: " + rolePersonId));
        
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule no encontrado con ID: " + scheduleId));
        
        SchedulePersonEntity entity = schedulePersonMapper.toEntity(schedulePerson);
        entity.setRolePerson(rolePerson);
        entity.setSchedule(schedule);
        
        SchedulePersonEntity savedEntity = schedulePersonRepository.save(entity);
        return schedulePersonMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<SchedulePerson> findOne(Long scheduleId, Long personId) {
        if (scheduleId == null || personId == null) {
            return Optional.empty();
        }
        return schedulePersonRepository.findByScheduleIdAndPersonId(scheduleId, personId)
                .map(schedulePersonMapper::toDomain);
    }
    
    @Override
    public List<SchedulePerson> findByPerson(Long personId) {
        if (personId == null) {
            return List.of();
        }
        return schedulePersonRepository.findByPersonIdWithRelations(personId).stream()
                .map(schedulePersonMapper::toDomain)
                .collect(Collectors.toList());
    }
}

