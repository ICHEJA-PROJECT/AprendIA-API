package com.icheha.aprendia_api.users.schedule.data.mappers;

import com.icheha.aprendia_api.users.schedule.data.entities.ScheduleEntity;
import com.icheha.aprendia_api.users.schedule.domain.entities.Schedule;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ScheduleMapper {
    
    private final SchedulePersonMapper schedulePersonMapper;
    
    public ScheduleMapper(SchedulePersonMapper schedulePersonMapper) {
        this.schedulePersonMapper = schedulePersonMapper;
    }
    
    public Schedule toDomain(ScheduleEntity entity) {
        if (entity == null) return null;
        
        return new Schedule.Builder()
                .id(entity.getId())
                .day(entity.getDay())
                .hour(entity.getHour())
                .schedulePeople(entity.getSchedulePeople() != null ? 
                        entity.getSchedulePeople().stream()
                                .map(schedulePersonMapper::toDomain)
                                .collect(Collectors.toList()) : null)
                .build();
    }
    
    public ScheduleEntity toEntity(Schedule domain) {
        if (domain == null) return null;
        
        ScheduleEntity entity = new ScheduleEntity();
        entity.setId(domain.getId());
        entity.setDay(domain.getDay());
        entity.setHour(domain.getHour());
        return entity;
    }
}

