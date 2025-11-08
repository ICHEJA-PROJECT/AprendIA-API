package com.icheha.aprendia_api.records.pupilExcerise.services.impl;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilSkillDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.CalculateGradesBySkillsResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.GradeSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilSkillResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.entities.PupilSkillEntity;
import com.icheha.aprendia_api.records.pupilExcerise.data.repositories.PupilSkillRepository;
import com.icheha.aprendia_api.records.pupilExcerise.services.IPupilSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PupilSkillServiceImpl implements IPupilSkillService {
    
    @Autowired
    private PupilSkillRepository pupilSkillRepository;
    
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
    
    @Override
    public List<CalculateGradesBySkillsResponseDto> calculateGradesBySkills(Integer pupilId, List<Integer> skillIds) {
        if (skillIds == null || skillIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        return skillIds.stream()
                .map(skillId -> {
                    Double grade = calculateGradeBySkill(pupilId.longValue(), skillId.longValue());
                    CalculateGradesBySkillsResponseDto response = new CalculateGradesBySkillsResponseDto();
                    response.setSkillId(skillId.longValue());
                    response.setGrade(grade);
                    return response;
                })
                .collect(Collectors.toList());
    }
    
    private Double calculateGradeBySkill(Long pupilId, Long skillId) {
        List<PupilSkillEntity> pupilSkills = pupilSkillRepository.findByPupilAndSkill(pupilId, skillId);
        
        if (pupilSkills == null || pupilSkills.isEmpty()) {
            return 0.0;
        }
        
        if (pupilSkills.size() == 1) {
            return pupilSkills.get(0).getScore();
        }
        
        // Filtrar solo los que tienen fecha de completado
        List<PupilSkillEntity> skillsWithDate = pupilSkills.stream()
                .filter(ps -> ps.getPupilExercise() != null && ps.getPupilExercise().getCompletedDate() != null)
                .collect(Collectors.toList());
        
        if (skillsWithDate.isEmpty()) {
            // Si no hay fechas, calcular promedio simple
            return pupilSkills.stream()
                    .mapToDouble(PupilSkillEntity::getScore)
                    .average()
                    .orElse(0.0);
        }
        
        // Ordenar por fecha descendente
        skillsWithDate.sort((a, b) -> {
            LocalDateTime dateA = a.getPupilExercise().getCompletedDate();
            LocalDateTime dateB = b.getPupilExercise().getCompletedDate();
            return dateB.compareTo(dateA);
        });
        
        LocalDateTime fechaReferencia = skillsWithDate.get(0).getPupilExercise().getCompletedDate();
        double factorDecaimiento = 0.3;
        double sumaProductos = 0.0;
        double sumaPesos = 0.0;
        
        for (PupilSkillEntity skill : skillsWithDate) {
            LocalDateTime fechaCompletado = skill.getPupilExercise().getCompletedDate();
            long diasTranscurridos = Math.abs(java.time.Duration.between(fechaReferencia, fechaCompletado).toDays());
            
            // Peso usando función logarítmica con decaimiento temporal
            // Peso = 1 / (1 + log(1 + días * factor))
            double peso = 1.0 / (1.0 + Math.log(1.0 + diasTranscurridos * factorDecaimiento));
            
            sumaProductos += skill.getScore() * peso;
            sumaPesos += peso;
        }
        
        return sumaPesos > 0 ? sumaProductos / sumaPesos : 0.0;
    }
}
