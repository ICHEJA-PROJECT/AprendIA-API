package com.icheha.aprendia_api.exercises.topics.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLearningPathDto {
    
    @NotNull(message = "ID de metodología es requerido")
    private Long idMetodologia;
    
    @NotNull(message = "ID de perfil es requerido")
    private Long idPerfil;
    
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    
    private String descripcion;
    
    private String urlImagen;
}

