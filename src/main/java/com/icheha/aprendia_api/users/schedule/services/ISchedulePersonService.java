package com.icheha.aprendia_api.users.schedule.services;

import com.icheha.aprendia_api.users.schedule.data.dtos.CreateSchedulePersonDto;
import com.icheha.aprendia_api.users.schedule.data.dtos.SchedulePersonResponseDto;

import java.util.List;

public interface ISchedulePersonService {
    
    SchedulePersonResponseDto create(CreateSchedulePersonDto createSchedulePersonDto);
    
    List<SchedulePersonResponseDto> findByPerson(Long personId);
}

