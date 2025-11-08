package com.icheha.aprendia_api.users.cell.data.repositories;

import com.icheha.aprendia_api.users.cell.data.entities.TeacherCellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCellRepository extends JpaRepository<TeacherCellEntity, TeacherCellEntity.TeacherCellId> {
}

