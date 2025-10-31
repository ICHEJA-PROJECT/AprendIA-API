package com.icheha.aprendia_api.records.pupilExcerise.data.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePupilTopicDto {
    
    @NotNull(message = "Pupil ID es requerido")
    private Long pupilId;
    
    @NotNull(message = "Topic ID es requerido")
    private Long topicId;
}
