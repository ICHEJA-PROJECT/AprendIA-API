package com.icheha.aprendia_api.users.schedule.domain.entities;

import java.util.List;
import java.util.Objects;

/**
 * Entidad de dominio Schedule
 * Representa un horario disponible en el sistema
 */
public class Schedule {
    
    private final Long id;
    private final String day;
    private final String hour;
    private final List<SchedulePerson> schedulePeople;
    
    private Schedule(Builder builder) {
        this.id = builder.id;
        this.day = builder.day;
        this.hour = builder.hour;
        this.schedulePeople = builder.schedulePeople;
    }
    
    public Long getId() { return id; }
    public String getDay() { return day; }
    public String getHour() { return hour; }
    public List<SchedulePerson> getSchedulePeople() { return schedulePeople; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public static class Builder {
        private Long id;
        private String day;
        private String hour;
        private List<SchedulePerson> schedulePeople;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder day(String day) {
            this.day = day;
            return this;
        }
        
        public Builder hour(String hour) {
            this.hour = hour;
            return this;
        }
        
        public Builder schedulePeople(List<SchedulePerson> schedulePeople) {
            this.schedulePeople = schedulePeople;
            return this;
        }
        
        public Schedule build() {
            return new Schedule(this);
        }
    }
}

