package com.icheha.aprendia_api.exercises.exercises.data.repositories;

import com.icheha.aprendia_api.exercises.exercises.data.entities.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    
    // Consulta para obtener peso por ejercicio y habilidad
    @Query(value = """
        SELECT ts.peso as weight
        FROM ejercicio e
        INNER JOIN reactivo p ON e.id_reactivo = p.id_reactivo
        INNER JOIN reactivo_habilidades ph ON p.id_reactivo = ph.id_reactivo
        INNER JOIN habilidad h ON ph.id_habilidad = h.id_agenda
        WHERE e.id_ejercicio = :exerciseId AND h.id_agenda = :skillId
        """, nativeQuery = true)
    Double findWeightByExerciseAndSkill(@Param("exerciseId") Long exerciseId, @Param("skillId") Long skillId);
    
    // Consulta para obtener todos los pesos de un ejercicio
    @Query(value = """
        SELECT ph.id_habilidad as skillId, ph.peso as weight
        FROM ejercicio e
        INNER JOIN reactivo p ON e.id_reactivo = p.id_reactivo
        INNER JOIN reactivo_habilidades ph ON p.id_reactivo = ph.id_reactivo
        WHERE e.id_ejercicio = :exerciseId
        """, nativeQuery = true)
    List<Object[]> findWeightsByExercise(@Param("exerciseId") Long exerciseId);
    
    // Consulta para contar ejercicios por template
    @Query(value = """
        SELECT p.id_reactivo as templateId, COUNT(DISTINCT e.id_ejercicio) as exerciseCount
        FROM ejercicio e
        INNER JOIN reactivo p ON e.id_reactivo = p.id_reactivo
        WHERE e.id_ejercicio IN :exerciseIds
        GROUP BY p.id_reactivo
        """, nativeQuery = true)
    List<Object[]> countExercisesByTemplate(@Param("exerciseIds") List<Long> exerciseIds);
    
    // Buscar ejercicios por template
    @Query("SELECT e FROM ExerciseEntity e WHERE e.template.id = :templateId")
    List<ExerciseEntity> findByTemplateId(@Param("templateId") Long templateId);
    
    // Buscar ejercicios por IDs
    @Query("SELECT e FROM ExerciseEntity e WHERE e.id IN :ids")
    List<ExerciseEntity> findByIds(@Param("ids") List<Long> ids);
}
