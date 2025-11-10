package com.icheha.aprendia_api.users.schedule.domain.repositories;

import com.icheha.aprendia_api.users.schedule.domain.entities.Schedule;

import java.util.List;
import java.util.Optional;

public interface IScheduleRepository {
    
    Schedule create(Schedule schedule);
    
    List<Schedule> findAll();
    
    Optional<Schedule> findById(Long id);
    
    Schedule update(Schedule schedule);
    
    void delete(Long id);
}

