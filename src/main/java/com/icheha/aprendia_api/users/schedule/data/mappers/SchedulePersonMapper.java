package com.icheha.aprendia_api.users.schedule.data.mappers;

import com.icheha.aprendia_api.auth.data.mappers.PersonaRolMapper;
import com.icheha.aprendia_api.users.schedule.data.entities.SchedulePersonEntity;
import com.icheha.aprendia_api.users.schedule.domain.entities.SchedulePerson;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SchedulePersonMapper {
    
    private final PersonaRolMapper personaRolMapper;
    private final ScheduleMapper scheduleMapper;
    
    public SchedulePersonMapper(PersonaRolMapper personaRolMapper, @Lazy ScheduleMapper scheduleMapper) {
        this.personaRolMapper = personaRolMapper;
        this.scheduleMapper = scheduleMapper;
    }
    
    public SchedulePerson toDomain(SchedulePersonEntity entity) {
        if (entity == null) return null;
        
        return new SchedulePerson.Builder()
                .rolePersonId(entity.getRolePersonId())
                .scheduleId(entity.getScheduleId())
                .rolePerson(entity.getRolePerson() != null ? personaRolMapper.toDomain(entity.getRolePerson()) : null)
                .schedule(entity.getSchedule() != null ? scheduleMapper.toDomain(entity.getSchedule()) : null)
                .build();
    }
    
    public SchedulePersonEntity toEntity(SchedulePerson domain) {
        if (domain == null) return null;
        
        SchedulePersonEntity entity = new SchedulePersonEntity();
        entity.setRolePersonId(domain.getRolePersonId());
        entity.setScheduleId(domain.getScheduleId());
        return entity;
    }
}

