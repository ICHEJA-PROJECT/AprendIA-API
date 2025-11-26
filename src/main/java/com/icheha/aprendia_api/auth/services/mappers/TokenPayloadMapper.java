package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.dtos.response.ParienteInfoDto;
import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class TokenPayloadMapper {
    
    public TokenPayloadDto toDto(Persona persona, String roleName, String disabilityName, Long disabilityId, Long learningPathId, Long studentId) {
        return toDto(persona, roleName, disabilityName, disabilityId, learningPathId, studentId, null, null);
    }
    
    public TokenPayloadDto toDto(Persona persona, String roleName, String disabilityName, Long disabilityId, Long learningPathId, Long studentId,
                                  ParienteInfoDto padre, ParienteInfoDto madre) {
        if (persona == null) return null;
        
        // Formatear nombre como en el original: primerNombre + segundoNombre (si existe)
        String nombre = persona.getPrimerNombre();
        if (persona.getSegundoNombre() != null && !persona.getSegundoNombre().trim().isEmpty()) {
            nombre = nombre + " " + persona.getSegundoNombre();
        }
        
        TokenPayloadDto dto = new TokenPayloadDto();
        dto.setIdPersona(persona.getIdPersona());
        dto.setNombre(nombre);
        // username es el CURP, verificar que no sea null
        dto.setUsername(persona.getCurp() != null ? persona.getCurp().getValue() : null);
        dto.setRoleName(roleName);
        dto.setDisabilityName(disabilityName);
        dto.setDisabilityId(disabilityId);
        dto.setLearningPathId(learningPathId);
        dto.setStudentId(studentId);
        dto.setIat(System.currentTimeMillis() / 1000); // Timestamp en segundos como en JWT estándar
        dto.setExp((System.currentTimeMillis() / 1000) + 3600); // 1 hora en segundos
        
        // Información de parientes
        dto.setPadre(padre);
        dto.setMadre(madre);
        
        return dto;
    }
    
    /**
     * Construye un ParienteInfoDto desde una Persona
     * Si la persona es null, retorna un DTO con existe = false
     */
    public ParienteInfoDto toParienteInfoDto(Persona persona) {
        if (persona == null) {
            return ParienteInfoDto.builder()
                    .existe(false)
                    .build();
        }
        
        // Construir nombre completo
        String nombreCompleto = persona.getNombreCompletoConApellidos();
        
        return ParienteInfoDto.builder()
                .existe(true)
                .idPersona(persona.getIdPersona())
                .primerNombre(persona.getPrimerNombre())
                .segundoNombre(persona.getSegundoNombre())
                .primerApellido(persona.getApellidoPaterno())
                .segundoApellido(persona.getApellidoMaterno())
                .nombreCompleto(nombreCompleto)
                .curp(persona.getCurp() != null ? persona.getCurp().getValue() : null)
                .email(persona.getEmail())
                .telefono(persona.getTelefono())
                .fechaNacimiento(persona.getFechaNacimiento())
                .genero(persona.getGenero() != null ? persona.getGenero().toString() : null)
                .numeroIne(persona.getNumeroIne())
                .build();
    }
}
