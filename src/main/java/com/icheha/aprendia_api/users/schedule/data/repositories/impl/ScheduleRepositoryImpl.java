package com.icheha.aprendia_api.users.schedule.data.repositories.impl;

import com.icheha.aprendia_api.users.schedule.data.entities.ScheduleEntity;
import com.icheha.aprendia_api.users.schedule.data.mappers.ScheduleMapper;
import com.icheha.aprendia_api.users.schedule.data.repositories.ScheduleRepository;
import com.icheha.aprendia_api.users.schedule.domain.entities.Schedule;
import com.icheha.aprendia_api.users.schedule.domain.repositories.IScheduleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ScheduleRepositoryImpl implements IScheduleRepository {
    
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    
    public ScheduleRepositoryImpl(@Lazy ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }
    
    @Override
    public Schedule create(Schedule schedule) {
        if (schedule == null) {
            throw new IllegalArgumentException("Schedule no puede ser nulo");
        }
        
        ScheduleEntity entity = scheduleMapper.toEntity(schedule);
        ScheduleEntity savedEntity = scheduleRepository.save(entity);
        return scheduleMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll().stream()
                .map(scheduleMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Schedule> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return scheduleRepository.findById(id)
                .map(scheduleMapper::toDomain);
    }
}

