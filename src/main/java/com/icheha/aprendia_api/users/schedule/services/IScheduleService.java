package com.icheha.aprendia_api.users.schedule.services;

import com.icheha.aprendia_api.users.schedule.data.dtos.CreateScheduleDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface IScheduleService {
    
    ScheduleResponseDto create(CreateScheduleDto createScheduleDto);
    
    List<ScheduleResponseDto> findAll();
    
    Optional<ScheduleResponseDto> findById(Long id);
}

