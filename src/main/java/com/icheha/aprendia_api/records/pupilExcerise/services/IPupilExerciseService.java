package com.icheha.aprendia_api.records.pupilExcerise.services;

import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.ExerciseResponseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.CreatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request.UpdatePupilExerciseDto;
import com.icheha.aprendia_api.records.pupilExcerise.data.dtos.response.PupilExerciseResponseDto;

import java.util.List;

public interface IPupilExerciseService {
    
    PupilExerciseResponseDto createPupilExercise(CreatePupilExerciseDto createPupilExerciseDto);
    
    List<Integer> getExerciseIdsByPupil(Integer id);
    
    List<ExerciseResponseDto> getAssignedExercisesByPupil(Integer id);
    
    List<PupilExerciseResponseDto> getExercisesByPupil(Integer id);
    
    List<PupilExerciseResponseDto> getExercisesByExerciseId(Integer id);
    
    PupilExerciseResponseDto updatePupilExercise(Integer id, UpdatePupilExerciseDto updatePupilExerciseDto);
}
