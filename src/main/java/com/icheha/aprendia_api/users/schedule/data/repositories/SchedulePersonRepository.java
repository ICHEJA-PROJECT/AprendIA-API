package com.icheha.aprendia_api.users.schedule.data.repositories;

import com.icheha.aprendia_api.users.schedule.data.entities.SchedulePersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchedulePersonRepository extends JpaRepository<SchedulePersonEntity, SchedulePersonEntity.SchedulePersonId> {
    
    @Query("SELECT sp FROM SchedulePersonEntity sp " +
           "WHERE sp.scheduleId = :scheduleId " +
           "AND sp.rolePerson.persona.idPersona = :personId")
    Optional<SchedulePersonEntity> findByScheduleIdAndPersonId(@Param("scheduleId") Long scheduleId, 
                                                                 @Param("personId") Long personId);
    
    @Query("SELECT sp FROM SchedulePersonEntity sp " +
           "JOIN FETCH sp.schedule " +
           "JOIN FETCH sp.rolePerson rp " +
           "JOIN FETCH rp.rol " +
           "WHERE sp.rolePerson.persona.idPersona = :personId")
    List<SchedulePersonEntity> findByPersonIdWithRelations(@Param("personId") Long personId);
}

