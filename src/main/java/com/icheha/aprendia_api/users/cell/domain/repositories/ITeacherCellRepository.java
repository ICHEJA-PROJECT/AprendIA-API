package com.icheha.aprendia_api.users.cell.domain.repositories;

import com.icheha.aprendia_api.users.cell.domain.entities.TeacherCell;

public interface ITeacherCellRepository {
    
    TeacherCell create(TeacherCell teacherCell, Long teacherId, Long cellId);
}

