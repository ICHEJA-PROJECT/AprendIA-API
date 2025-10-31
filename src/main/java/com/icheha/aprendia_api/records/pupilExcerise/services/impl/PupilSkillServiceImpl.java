package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilSkillDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.GradeSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilSkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PupilSkillServiceImpl implements IPupilSkillService {
    
    @Override
    public PupilSkillResponseDto createPupilSkill(CreatePupilSkillDto createPupilSkillDto) {
        PupilSkillResponseDto pupilSkill = new PupilSkillResponseDto();
        pupilSkill.setId(1L);
        pupilSkill.setPupilExerciseId(createPupilSkillDto.getPupilExerciseId());
        pupilSkill.setPupilName("Pupil Mock");
        pupilSkill.setExerciseName("Exercise Mock");
        pupilSkill.setSkillId(createPupilSkillDto.getSkillId());
        pupilSkill.setSkillName("Skill Mock");
        pupilSkill.setScore(createPupilSkillDto.getScore());
        return pupilSkill;
    }
    
    @Override
    public List<PupilSkillResponseDto> createManyPupilSkills(List<CreatePupilSkillDto> createPupilSkillDtos) {
        List<PupilSkillResponseDto> pupilSkills = new ArrayList<>();
        for (int i = 0; i < createPupilSkillDtos.size(); i++) {
            CreatePupilSkillDto dto = createPupilSkillDtos.get(i);
            PupilSkillResponseDto pupilSkill = new PupilSkillResponseDto();
            pupilSkill.setId((long) (i + 1));
            pupilSkill.setPupilExerciseId(dto.getPupilExerciseId());
            pupilSkill.setPupilName("Pupil Mock");
            pupilSkill.setExerciseName("Exercise Mock");
            pupilSkill.setSkillId(dto.getSkillId());
            pupilSkill.setSkillName("Skill Mock");
            pupilSkill.setScore(dto.getScore());
            pupilSkills.add(pupilSkill);
        }
        return pupilSkills;
    }
    
    @Override
    public List<PupilSkillResponseDto> getAllPupilSkills() {
        List<PupilSkillResponseDto> pupilSkills = new ArrayList<>();
        
        PupilSkillResponseDto pupilSkill1 = new PupilSkillResponseDto();
        pupilSkill1.setId(1L);
        pupilSkill1.setPupilExerciseId(1L);
        pupilSkill1.setPupilName("Pupil 1");
        pupilSkill1.setExerciseName("Exercise 1");
        pupilSkill1.setSkillId(1L);
        pupilSkill1.setSkillName("Skill 1");
        pupilSkill1.setScore(85.5);
        pupilSkills.add(pupilSkill1);
        
        return pupilSkills;
    }
    
    @Override
    public List<PupilSkillResponseDto> getSkillsByPupilId(Integer id) {
        return getAllPupilSkills();
    }
    
    @Override
    public List<GradeSkillResponseDto> getGradeSkills() {
        List<GradeSkillResponseDto> gradeSkills = new ArrayList<>();
        
        GradeSkillResponseDto gradeSkill1 = new GradeSkillResponseDto();
        gradeSkill1.setId(1L);
        gradeSkill1.setGradeName("Grade 1");
        gradeSkill1.setSkillId(1L);
        gradeSkill1.setSkillName("Skill 1");
        gradeSkill1.setAverageScore(85.5);
        gradeSkill1.setTotalStudents(25);
        gradeSkills.add(gradeSkill1);
        
        return gradeSkills;
    }
}
