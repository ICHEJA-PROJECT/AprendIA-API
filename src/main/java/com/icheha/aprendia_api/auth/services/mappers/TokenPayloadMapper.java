package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class TokenPayloadMapper {
    
    public TokenPayloadDto toDto(Persona persona, String roleName, String disabilityName, Long disabilityId, Long learningPathId, Long studentId) {
        if (persona == null) return null;
        
        // Formatear nombre como en el original: primerNombre + segundoNombre (si existe)
        String nombre = persona.getPrimerNombre();
        if (persona.getSegundoNombre() != null && !persona.getSegundoNombre().trim().isEmpty()) {
            nombre = nombre + " " + persona.getSegundoNombre();
        }
        
        TokenPayloadDto dto = new TokenPayloadDto();
        dto.setIdPersona(persona.getIdPersona());
        dto.setNombre(nombre);
        dto.setUsername(persona.getCurp().getValue()); // username es el CURP
        dto.setRoleName(roleName);
        dto.setDisabilityName(disabilityName);
        dto.setDisabilityId(disabilityId);
        dto.setLearningPathId(learningPathId);
        dto.setStudentId(studentId);
        dto.setIat(System.currentTimeMillis() / 1000); // Timestamp en segundos como en JWT estándar
        dto.setExp((System.currentTimeMillis() / 1000) + 3600); // 1 hora en segundos
        return dto;
    }
}
