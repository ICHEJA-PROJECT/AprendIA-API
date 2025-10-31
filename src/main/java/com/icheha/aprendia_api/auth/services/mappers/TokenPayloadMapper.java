package com.icheha.aprendia_api.auth.services.mappers;

import com.icheha.aprendia_api.auth.data.dtos.response.TokenPayloadDto;
import com.icheha.aprendia_api.auth.domain.entities.Persona;
import org.springframework.stereotype.Component;

@Component
public class TokenPayloadMapper {
    
    public TokenPayloadDto toDto(Persona persona, String roleName, String disabilityName, Long disabilityId, Long learningPathId) {
        if (persona == null) return null;
        
        TokenPayloadDto dto = new TokenPayloadDto();
        dto.setIdPersona(persona.getIdPersona());
        dto.setNombre(persona.getPrimerNombre() + " " + persona.getApellidoPaterno());
        dto.setUsername(persona.getCurp().getValue());
        dto.setRoleName(roleName);
        dto.setDisabilityName(disabilityName);
        dto.setDisabilityId(disabilityId);
        dto.setLearningPathId(learningPathId);
        dto.setIat(System.currentTimeMillis());
        dto.setExp(System.currentTimeMillis() + 3600000); // 1 hora
        return dto;
    }
}
