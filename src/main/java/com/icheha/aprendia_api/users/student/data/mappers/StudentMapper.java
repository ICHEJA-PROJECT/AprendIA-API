package com.icheha.aprendia_api.users.student.data.mappers;

import com.icheha.aprendia_api.auth.domain.entities.Persona;
import com.icheha.aprendia_api.users.person.data.mappers.PersonaMapper;
import com.icheha.aprendia_api.users.student.data.entities.StudentEntity;
import com.icheha.aprendia_api.users.student.domain.entities.Progenitor;
import com.icheha.aprendia_api.users.student.domain.entities.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    
    private final PersonaMapper personaMapper;
    private final ProgenitorMapper progenitorMapper;
    
    public StudentMapper(@Qualifier("userPersonaMapper") PersonaMapper personaMapper, ProgenitorMapper progenitorMapper) {
        this.personaMapper = personaMapper;
        this.progenitorMapper = progenitorMapper;
    }
    
    public Student toDomain(StudentEntity entity) {
        if (entity == null) return null;
        
        Persona persona = personaMapper.toDomain(entity.getPersona());
        Persona teacher = entity.getTeacher() != null ? personaMapper.toDomain(entity.getTeacher()) : null;
        Progenitor father = progenitorMapper.toDomain(entity.getFather());
        Progenitor mother = progenitorMapper.toDomain(entity.getMother());
        
        return new Student.Builder()
                .id(entity.getId())
                .persona(persona)
                .teacher(teacher)
                .qrPath(entity.getQrPath())
                .father(father)
                .mother(mother)
                .build();
    }
    
    public StudentEntity toEntity(Student domain) {
        if (domain == null) return null;
        
        StudentEntity entity = new StudentEntity();
        entity.setId(domain.getId());
        entity.setQrPath(domain.getQrPath());
        // Las relaciones se establecerán desde fuera
        return entity;
    }
}

