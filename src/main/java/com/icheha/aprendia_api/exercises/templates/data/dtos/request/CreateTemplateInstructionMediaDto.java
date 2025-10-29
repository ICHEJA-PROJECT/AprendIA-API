package com.icheha.aprendia_api.exercises.templates.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemplateInstructionMediaDto {
    
    @NotNull(message = "Template ID es requerido")
    private Long templateId;
    
    @NotNull(message = "Instruction Media ID es requerido")
    private Long instructionMediaId;
    
    @NotBlank(message = "Media URL es requerido")
    private String mediaUrl;
    
    @NotNull(message = "Order es requerido")
    private Integer order;
}
