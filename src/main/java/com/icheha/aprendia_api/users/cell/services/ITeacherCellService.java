package com.icheha.aprendia_api.users.cell.services;

import com.icheha.aprendia_api.users.cell.data.dtos.CreateTeacherCellDto;
import com.icheha.aprendia_api.users.cell.data.dtos.TeacherCellResponseDto;

public interface ITeacherCellService {
    
    TeacherCellResponseDto create(CreateTeacherCellDto createTeacherCellDto);
}

