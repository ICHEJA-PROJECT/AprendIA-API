package com.icheha.aprendia_api.users.schedule.domain.entities;

import com.icheha.aprendia_api.auth.domain.entities.PersonaRol;
import java.util.Objects;

/**
 * Entidad de dominio SchedulePerson
 * Representa la relación entre un horario y una persona con rol
 */
public class SchedulePerson {
    
    private final Long rolePersonId;
    private final Long scheduleId;
    private final PersonaRol rolePerson;
    private final Schedule schedule;
    
    private SchedulePerson(Builder builder) {
        this.rolePersonId = builder.rolePersonId;
        this.scheduleId = builder.scheduleId;
        this.rolePerson = builder.rolePerson;
        this.schedule = builder.schedule;
    }
    
    public Long getRolePersonId() { return rolePersonId; }
    public Long getScheduleId() { return scheduleId; }
    public PersonaRol getRolePerson() { return rolePerson; }
    public Schedule getSchedule() { return schedule; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulePerson that = (SchedulePerson) o;
        return Objects.equals(rolePersonId, that.rolePersonId) &&
               Objects.equals(scheduleId, that.scheduleId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(rolePersonId, scheduleId);
    }
    
    public static class Builder {
        private Long rolePersonId;
        private Long scheduleId;
        private PersonaRol rolePerson;
        private Schedule schedule;
        
        public Builder rolePersonId(Long rolePersonId) {
            this.rolePersonId = rolePersonId;
            return this;
        }
        
        public Builder scheduleId(Long scheduleId) {
            this.scheduleId = scheduleId;
            return this;
        }
        
        public Builder rolePerson(PersonaRol rolePerson) {
            this.rolePerson = rolePerson;
            return this;
        }
        
        public Builder schedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }
        
        public SchedulePerson build() {
            return new SchedulePerson(this);
        }
    }
}

