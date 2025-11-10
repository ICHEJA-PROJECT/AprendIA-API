package com.icheha.aprendia_api.users.schedule.services.impl;

import com.icheha.aprendia_api.users.schedule.data.dtos.CreateScheduleDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.ScheduleResponseDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.UpdateScheduleDto;
import com.icheha.aprendia_api.users.schedule.data.mappers.ScheduleMapper;
import com.icheha.aprendia_api.users.schedule.domain.entities.Schedule;
import com.icheha.aprendia_api.users.schedule.domain.repositories.IScheduleRepository;
import com.icheha.aprendia_api.users.schedule.services.IScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements IScheduleService {
    
    private final IScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    
    public ScheduleServiceImpl(IScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }
    
    @Override
    @Transactional
    public ScheduleResponseDto create(CreateScheduleDto createScheduleDto) {
        Schedule schedule = new Schedule.Builder()
                .day(createScheduleDto.getDay())
                .hour(createScheduleDto.getHour())
                .build();
        
        Schedule saved = scheduleRepository.create(schedule);
        return toResponseDto(saved);
    }
    
    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ScheduleResponseDto> findById(Long id) {
        return scheduleRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    @Override
    @Transactional
    public ScheduleResponseDto update(Long id, UpdateScheduleDto updateScheduleDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horario no encontrado con ID: " + id));
        
        Schedule.Builder builder = new Schedule.Builder()
                .id(schedule.getId())
                .day(updateScheduleDto.getDay() != null ? updateScheduleDto.getDay() : schedule.getDay())
                .hour(updateScheduleDto.getHour() != null ? updateScheduleDto.getHour() : schedule.getHour())
                .schedulePeople(schedule.getSchedulePeople());
        
        Schedule updated = scheduleRepository.update(builder.build());
        return toResponseDto(updated);
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        scheduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horario no encontrado con ID: " + id));
        scheduleRepository.delete(id);
    }
    
    private ScheduleResponseDto toResponseDto(Schedule schedule) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setDay(schedule.getDay());
        dto.setHour(schedule.getHour());
        return dto;
    }
}

