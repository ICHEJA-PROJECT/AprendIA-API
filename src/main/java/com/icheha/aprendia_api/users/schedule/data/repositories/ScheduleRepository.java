package com.icheha.aprendia_api.users.schedule.data.repositories;

import com.icheha.aprendia_api.users.schedule.data.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}

