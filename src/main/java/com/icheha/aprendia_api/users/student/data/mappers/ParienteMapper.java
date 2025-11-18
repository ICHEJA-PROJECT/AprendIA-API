package com.icheha.aprendia_api.users.student.data.mappers;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.student.data.entities.ParienteEntity;
import com.icheha.aprendia_api.users.student.domain.entities.Pariente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ParienteMapper {
    
    private final PersonaMapper personaMapper;
    private final RolParienteMapper rolParienteMapper;
    
    public ParienteMapper(@Qualifier("userPersonaMapper") PersonaMapper personaMapper, 
                         RolParienteMapper rolParienteMapper) {
        this.personaMapper = personaMapper;
        this.rolParienteMapper = rolParienteMapper;
    }
    
    public Pariente toDomain(ParienteEntity entity) {
        if (entity == null) return null;
        
        Persona persona = personaMapper.toDomain(entity.getPersona());
        Persona pariente = personaMapper.toDomain(entity.getPariente());
        
        return new Pariente.Builder()
                .id(entity.getId())
                .persona(persona)
                .pariente(pariente)
                .rolPariente(rolParienteMapper.toDomain(entity.getRolPariente()))
                .build();
    }
    
    public ParienteEntity toEntity(Pariente domain) {
        if (domain == null) return null;
        
        ParienteEntity entity = new ParienteEntity();
        entity.setId(domain.getId());
        // Las relaciones se establecerán desde fuera
        return entity;
    }
}

