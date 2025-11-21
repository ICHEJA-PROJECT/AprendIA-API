package com.icheha.aprendia_api.preferences.impairments.data.repositories;

import com.icheha.aprendia_api.preferences.impairments.data.entities.StudentImpairment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentImpairmentRepository extends JpaRepository<StudentImpairment, StudentImpairment.StudentImpairmentId> {
    
    @Query("SELECT si FROM StudentImpairment si JOIN FETCH si.impairment WHERE si.studentId = :studentId")
    List<StudentImpairment> findByStudentIdWithImpairment(@Param("studentId") Long studentId);
    
    @Query("SELECT si FROM StudentImpairment si WHERE si.studentId = :studentId")
    List<StudentImpairment> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT si.studentId FROM StudentImpairment si WHERE si.impairmentId = :impairmentId")
    List<Long> findStudentIdsByImpairmentId(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT si.impairmentId FROM StudentImpairment si WHERE si.studentId = :studentId")
    List<Long> findImpairmentIdsByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT si FROM StudentImpairment si WHERE si.impairmentId = :impairmentId")
    List<StudentImpairment> findByImpairmentId(@Param("impairmentId") Long impairmentId);
    
    @Query("SELECT si FROM StudentImpairment si JOIN FETCH si.impairment")
    List<StudentImpairment> findAllWithImpairment();
    
    @Query("SELECT si FROM StudentImpairment si JOIN FETCH si.impairment WHERE si.studentId = :studentId")
    List<StudentImpairment> findByStudentIdWithImpairmentForList(@Param("studentId") Long studentId);
    
    @Query("SELECT si FROM StudentImpairment si JOIN FETCH si.impairment WHERE si.studentId = :studentId AND si.impairmentId = :impairmentId")
    java.util.Optional<StudentImpairment> findByStudentIdAndImpairmentId(@Param("studentId") Long studentId, @Param("impairmentId") Long impairmentId);
    
    boolean existsByStudentIdAndImpairmentId(Long studentId, Long impairmentId);
    
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("DELETE FROM StudentImpairment si WHERE si.studentId = :studentId AND si.impairmentId = :impairmentId")
    void deleteByStudentIdAndImpairmentId(@Param("studentId") Long studentId, @Param("impairmentId") Long impairmentId);
    
    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query("DELETE FROM StudentImpairment si WHERE si.studentId = :studentId")
    void deleteByStudentId(@Param("studentId") Long studentId);
}
