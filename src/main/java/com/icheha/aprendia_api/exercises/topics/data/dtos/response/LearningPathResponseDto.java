package com.icheha.aprendia_api.exercises.topics.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPathResponseDto {
    
    private Long id;
    private Long idMetodologia;
    private String nombreMetodologia;
    private Long idPerfil;
    private String nombrePerfil;
    private String nombre;
    private String descripcion;
    private String urlImagen;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
