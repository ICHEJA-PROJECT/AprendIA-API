package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTemplateInstructionMediaDto {
    private Long templateId;
    private Long typeMediaId;
    private String mediaUrl;
}

