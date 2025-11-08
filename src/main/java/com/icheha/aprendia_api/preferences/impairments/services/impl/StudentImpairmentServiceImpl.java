package com.icheha.aprendia_api.preferences.impairments.services.impl;

import com.icheha.aprendia_api.preferences.impairments.data.dtos.request.CreateStudentImpairmentDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentDetailsResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.dtos.response.StudentImpairmentResponseDto;
import com.icheha.aprendia_api.preferences.impairments.data.mappers.StudentImpairmentMapper;
import com.icheha.aprendia_api.preferences.impairments.domain.entities.StudentImpairment;
import com.icheha.aprendia_api.preferences.impairments.domain.repositories.IStudentImpairmentRepository;
import com.icheha.aprendia_api.preferences.impairments.services.IStudentImpairmentService;
import com.icheha.aprendia_api.users.student.domain.entities.Student;
import com.icheha.aprendia_api.users.student.domain.repositories.IStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentImpairmentServiceImpl implements IStudentImpairmentService {
    
    private final IStudentImpairmentRepository studentImpairmentRepository;
    private final IStudentRepository studentRepository;
    private final StudentImpairmentMapper studentImpairmentMapper;
    
    public StudentImpairmentServiceImpl(IStudentImpairmentRepository studentImpairmentRepository,
                                       IStudentRepository studentRepository,
                                       StudentImpairmentMapper studentImpairmentMapper) {
        this.studentImpairmentRepository = studentImpairmentRepository;
        this.studentRepository = studentRepository;
        this.studentImpairmentMapper = studentImpairmentMapper;
    }
    
    @Override
    public StudentImpairmentDetailsResponseDto getStudentPreferencesWithDetails(Integer id) {
        Long personId = Long.valueOf(id);
        
        // Obtener estudiante por personId
        Optional<Student> studentOpt = 
                studentRepository.findByPersonId(personId);
        
        if (studentOpt.isEmpty()) {
            // Si no es estudiante, retornar null o respuesta vacía
            return null;
        }
        
        Student student = studentOpt.get();
        Long studentId = student.getId();
        
        // Obtener discapacidades del estudiante
        List<StudentImpairment> studentImpairments = 
                studentImpairmentRepository.findByStudentIdWithImpairment(personId);
        
        // Obtener nombre del estudiante
        String studentName = student.getPersona() != null ? 
                student.getPersona().getPrimerNombre() + " " + 
                student.getPersona().getApellidoPaterno() : "Estudiante " + studentId;
        
        // Mapear a DTO usando el mapper real
        StudentImpairmentDetailsResponseDto response = studentImpairmentMapper.toResponseDto(
                studentId, studentImpairments, studentName);
        
        return response;
    }
    
    @Override
    public StudentImpairmentResponseDto create(CreateStudentImpairmentDto dto) {
        StudentImpairment studentImpairment = 
                StudentImpairment.builder()
                        .studentId(dto.getStudentId())
                        .impairmentId(dto.getImpairmentId())
                        .build();
        
        StudentImpairment saved = 
                studentImpairmentRepository.save(studentImpairment);
        
        StudentImpairmentResponseDto response = new StudentImpairmentResponseDto();
        response.setStudentId(saved.getStudentId());
        response.setImpairmentId(saved.getImpairmentId());
        return response;
    }
    
    @Override
    public List<StudentImpairmentResponseDto> findAll() {
        List<StudentImpairment> entities = 
                studentImpairmentRepository.findAll();
        return entities.stream()
                .map(entity -> {
                    StudentImpairmentResponseDto dto = new StudentImpairmentResponseDto();
                    dto.setStudentId(entity.getStudentId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Long> findByImpairmentOnlyIds(Long impairmentId) {
        return studentImpairmentRepository.findStudentIdsByImpairmentId(impairmentId);
    }
    
    @Override
    public List<Long> findByStudentOnlyIds(Long studentId) {
        return studentImpairmentRepository.findImpairmentIdsByStudentId(studentId);
    }
    
    @Override
    public List<StudentImpairmentResponseDto> findByStudent(Long studentId) {
        List<StudentImpairment> entities = 
                studentImpairmentRepository.findByStudentId(studentId);
        return entities.stream()
                .map(entity -> {
                    StudentImpairmentResponseDto dto = new StudentImpairmentResponseDto();
                    dto.setStudentId(entity.getStudentId());
                    dto.setImpairmentId(entity.getImpairmentId());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
