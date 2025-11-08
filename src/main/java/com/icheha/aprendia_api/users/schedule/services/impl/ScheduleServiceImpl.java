package com.icheha.aprendia_api.users.schedule.services.impl;

import com.icheha.aprendia_api.users.schedule.data.dtos.CreateScheduleDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.ScheduleResponseDto;
import com.icheha.aprendia_api.users.schedule.data.mappers.ScheduleMapper;
import com.icheha.aprendia_api.users.schedule.domain.entities.Schedule;
import com.icheha.aprendia_api.users.schedule.domain.repositories.IScheduleRepository;
import com.icheha.aprendia_api.users.schedule.services.IScheduleService;
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
    public Optional<ScheduleResponseDto> findById(Long id) {
        return scheduleRepository.findById(id)
                .map(this::toResponseDto);
    }
    
    private ScheduleResponseDto toResponseDto(Schedule schedule) {
        ScheduleResponseDto dto = new ScheduleResponseDto();
        dto.setId(schedule.getId());
        dto.setDay(schedule.getDay());
        dto.setHour(schedule.getHour());
        return dto;
    }
}

