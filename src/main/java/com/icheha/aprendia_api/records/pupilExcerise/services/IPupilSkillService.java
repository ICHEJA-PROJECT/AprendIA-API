package com.icheha.aprendia_api.records.pupilExcerise.services;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilSkillDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.CalculateGradesBySkillsResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.GradeSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilSkillResponseDto;

import java.util.List;

public interface IPupilSkillService {
    
    PupilSkillResponseDto createPupilSkill(CreatePupilSkillDto createPupilSkillDto);
    
    List<PupilSkillResponseDto> createManyPupilSkills(List<CreatePupilSkillDto> createPupilSkillDtos);
    
    List<PupilSkillResponseDto> getAllPupilSkills();
    
    List<PupilSkillResponseDto> getSkillsByPupilId(Integer id);
    
    List<GradeSkillResponseDto> getGradeSkills();
    
    List<CalculateGradesBySkillsResponseDto> calculateGradesBySkills(Integer pupilId, List<Integer> skillIds);
}
