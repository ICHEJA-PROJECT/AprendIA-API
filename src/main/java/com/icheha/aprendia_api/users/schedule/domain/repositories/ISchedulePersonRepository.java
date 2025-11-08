package com.icheha.aprendia_api.users.schedule.domain.repositories;

import com.icheha.aprendia_api.users.schedule.domain.entities.SchedulePerson;

import java.util.List;
import java.util.Optional;

public interface ISchedulePersonRepository {
    
    SchedulePerson create(SchedulePerson schedulePerson, Long rolePersonId, Long scheduleId);
    
    Optional<SchedulePerson> findOne(Long scheduleId, Long personId);
    
    List<SchedulePerson> findByPerson(Long personId);
}

