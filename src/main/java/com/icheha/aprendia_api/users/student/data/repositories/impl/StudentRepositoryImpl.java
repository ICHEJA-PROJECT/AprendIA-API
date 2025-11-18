package com.icheha.aprendia_api.users.student.data.repositories.impl;

import com.icheha.aprendia_api.users.person.data.entities.PersonaEntity;
import com.icheha.aprendia_api.users.person.data.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import com.icheha.aprendia_api.users.student.data.entities.StudentEntity;
import com.icheha.aprendia_api.users.student.data.mappers.StudentMapper;
import com.icheha.aprendia_api.users.student.data.repositories.StudentRepository;
import com.icheha.aprendia_api.users.student.domain.entities.Student;
import com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements IStudentRepository {
    
    private final StudentRepository studentRepository;
    private final PersonaRepository personaRepository;
    private final StudentMapper studentMapper;
    
    public StudentRepositoryImpl(@Lazy StudentRepository studentRepository,
                                @Lazy @Qualifier("userPersonaRepository") PersonaRepository personaRepository,
                                StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.personaRepository = personaRepository;
        this.studentMapper = studentMapper;
    }
    
    @Override
    public Student create(Student student, Long personId, Long teacherId, String qrPath) {
        return create(student, personId, teacherId, qrPath, null);
    }
    
    @Override
    public Student create(Student student, Long personId, Long teacherId, String qrPath, Long createdByUserId) {
        if (student == null) {
            throw new IllegalArgumentException("Student no puede ser nulo");
        }
        if (personId == null) {
            throw new IllegalArgumentException("ID de persona no puede ser nulo");
        }
        
        PersonaEntity persona = personaRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + personId));
        
        PersonaEntity teacher = null;
        if (teacherId != null) {
            teacher = personaRepository.findById(teacherId)
                    .orElseThrow(() -> new IllegalArgumentException("Educador no encontrado con ID: " + teacherId));
        }
        
        PersonaEntity createdBy = null;
        if (createdByUserId != null) {
            createdBy = personaRepository.findById(createdByUserId)
                    .orElse(null); // No lanzar excepción si no se encuentra, solo no asignar
        }
        
        StudentEntity entity = studentMapper.toEntity(student);
        entity.setPersona(persona);
        entity.setTeacher(teacher);
        entity.setQrPath(qrPath != null ? qrPath : "pending");
        entity.setCreatedBy(createdBy);
        
        StudentEntity savedEntity = studentRepository.save(entity);
        return studentMapper.toDomain(savedEntity);
    }
    
    @Override
    public List<Student> findByTeacher(Long teacherId) {
        if (teacherId == null) {
            return List.of();
        }
        return studentRepository.findByTeacherId(teacherId).stream()
                .map(studentMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void updateQrPath(Long studentId, String qrPath) {
        if (studentId == null) {
            throw new IllegalArgumentException("ID de estudiante no puede ser nulo");
        }
        if (qrPath == null || qrPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Ruta del QR no puede ser nula o vacía");
        }
        
        StudentEntity entity = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + studentId));
        entity.setQrPath(qrPath);
        studentRepository.save(entity);
    }
    
    @Override
    public List<Student> findByCurp(String curp) {
        // TODO: CURP eliminado de PersonaEntity - implementar búsqueda alternativa si es necesario
        // Por ahora retornamos lista vacía ya que CURP ya no está disponible en PersonaEntity
        // Si se requiere esta funcionalidad, se debe buscar en PersonaEntity por CURP
        return List.of();
    }
    
    @Override
    public List<Student> findByName(String firstName, String paternalSurname) {
        if (firstName == null || paternalSurname == null) {
            return List.of();
        }
        return studentRepository.findByPersonaName(firstName, paternalSurname).stream()
                .map(studentMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Student> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return studentRepository.findByIdWithRelations(id)
                .map(studentMapper::toDomain);
    }
    
    @Override
    public Optional<Student> findByPersonId(Long personId) {
        if (personId == null) {
            return Optional.empty();
        }
        return studentRepository.findByPersonId(personId)
                .map(studentMapper::toDomain);
    }
    
    @Override
    public List<String> findUniqueNames() {
        return studentRepository.findUniqueFirstNames();
    }
    
    @Override
    public Student update(Long studentId, Long teacherId, String qrPath) {
        if (studentId == null) {
            throw new IllegalArgumentException("ID de estudiante no puede ser nulo");
        }
        
        StudentEntity entity = studentRepository.findByIdWithRelations(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + studentId));
        
        // Actualizar educador si se proporciona
        if (teacherId != null) {
            PersonaEntity teacher = personaRepository.findById(teacherId)
                    .orElseThrow(() -> new IllegalArgumentException("Educador no encontrado con ID: " + teacherId));
            entity.setTeacher(teacher);
        }
        
        // Actualizar QR path si se proporciona
        if (qrPath != null && !qrPath.trim().isEmpty()) {
            entity.setQrPath(qrPath);
        }
        
        StudentEntity savedEntity = studentRepository.save(entity);
        return studentMapper.toDomain(savedEntity);
    }
    
    @Override
    public void delete(Long studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("ID de estudiante no puede ser nulo");
        }
        
        StudentEntity entity = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + studentId));
        
        studentRepository.delete(entity);
    }
    
    @Override
    public List<Student> findAll() {
        return studentRepository.findAllWithRelations().stream()
                .map(studentMapper::toDomain)
                .collect(Collectors.toList());
    }
}

