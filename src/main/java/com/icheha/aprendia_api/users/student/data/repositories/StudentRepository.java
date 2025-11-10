package com.icheha.aprendia_api.users.student.data.repositories;

import com.icheha.aprendia_api.users.student.data.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
    @Query("SELECT s FROM StudentEntity s WHERE s.teacher.idPersona = :teacherId")
    List<StudentEntity> findByTeacherId(@Param("teacherId") Long teacherId);
    
    // TODO: CURP eliminado de PersonaEntity - implementar búsqueda alternativa si es necesario
    // @Query("SELECT s FROM StudentEntity s WHERE s.persona.curp LIKE CONCAT('%', :curp, '%')")
    // List<StudentEntity> findByPersonaCurpContaining(@Param("curp") String curp);
    
    @Query("SELECT s FROM StudentEntity s WHERE s.persona.primerNombre LIKE CONCAT('%', :firstName, '%') AND s.persona.primerApellido LIKE CONCAT('%', :paternalSurname, '%')")
    List<StudentEntity> findByPersonaName(@Param("firstName") String firstName, @Param("paternalSurname") String paternalSurname);
    
    @Query("SELECT DISTINCT s.persona.primerNombre FROM StudentEntity s")
    List<String> findUniqueFirstNames();
    
    @Query("SELECT s FROM StudentEntity s " +
           "LEFT JOIN FETCH s.persona p " +
           "LEFT JOIN FETCH s.teacher " +
           "LEFT JOIN FETCH s.father " +
           "LEFT JOIN FETCH s.mother " +
           "WHERE s.id = :id")
    Optional<StudentEntity> findByIdWithRelations(@Param("id") Long id);
    
    @Query("SELECT s FROM StudentEntity s " +
           "LEFT JOIN FETCH s.persona " +
           "WHERE s.persona.idPersona = :personId")
    Optional<StudentEntity> findByPersonId(@Param("personId") Long personId);
    
    @Query("SELECT s FROM StudentEntity s " +
           "LEFT JOIN FETCH s.persona p " +
           "LEFT JOIN FETCH s.teacher " +
           "LEFT JOIN FETCH s.father " +
           "LEFT JOIN FETCH s.mother")
    List<StudentEntity> findAllWithRelations();
}

